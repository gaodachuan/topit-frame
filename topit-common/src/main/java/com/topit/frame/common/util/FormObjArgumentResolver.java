package com.topit.frame.common.util;

import java.lang.annotation.Annotation;
import java.util.Enumeration;

import javax.servlet.ServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import com.topit.frame.common.view.servlet.FormObj;

public class FormObjArgumentResolver implements HandlerMethodArgumentResolver{

	public boolean supportsParameter(MethodParameter parameter) {
		
		return parameter.hasParameterAnnotation(FormObj.class);
	}

	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
  
	   FormObj formObj=parameter.getParameterAnnotation(FormObj.class);	
	   String alias=getAlias(formObj,parameter);
	   
	   //拿到obj,先从ModelAndViewContainer中拿，若没有则new一个参数类型的实例
	   Object obj=(mavContainer.containsAttribute(alias))?mavContainer.getModel().get(alias):createAttribute(alias,parameter,binderFactory,webRequest);
	   //获得WebDataBinder,这里的具体WebDataBinder是ExtenedServletRequestDataBinder
	   WebDataBinder binder=binderFactory.createBinder(webRequest, obj, alias);
	   Object target=binder.getTarget();
	   if(target!=null)
	   {
		 //绑定参数
		 bindParameters(webRequest,binder,alias);
		 //JSR303验证
		 validateIfApplication(binder,parameter);
		 if(binder.getBindingResult().hasErrors())
		 {
			 if(isBindExceptionRequried(binder,parameter))
			 {
				 
			 }
		 }
	   }
	   return null;
	}

	private boolean isBindExceptionRequried(WebDataBinder binder,
			MethodParameter parameter) {
		int i=parameter.getParameterIndex();
		return false;
	}

	private void validateIfApplication(WebDataBinder binder,
			MethodParameter parameter) {
		Annotation[] annotations=parameter.getParameterAnnotations();
		for(Annotation annot:annotations)
		{
			Object hints=AnnotationUtils.getValue(annot);
			binder.validate(hints instanceof Object[] ? (Object[])hints:new Object[]{hints});
		    break;
		}
		
		
	}

	private void bindParameters(NativeWebRequest request,
			WebDataBinder binder, String alias) {
		ServletRequest servletRequest=request.getNativeRequest(ServletRequest.class);
		MockHttpServletRequest newRequest=new MockHttpServletRequest();
		Enumeration<String> enu=servletRequest.getParameterNames();
		while(enu.hasMoreElements())
		{
			String paramName=enu.nextElement();
			if(paramName.startsWith(alias))
			{
				newRequest.setParameter(paramName.substring(alias.length()+1), request.getParameter(paramName));
			}
		}
		((ExtendedServletRequestDataBinder)binder).bind(newRequest);
	}

	private Object createAttribute(String alias, MethodParameter parameter,
			WebDataBinderFactory binderFactory, NativeWebRequest webRequest) {
		
		return BeanUtils.instantiateClass(parameter.getParameterType());
	}

	private String getAlias(FormObj formObj, MethodParameter parameter) {
		//得到FormObj的属性value,也就是对象参数的简称
		String alias=formObj.value();
		if(alias==null || StringUtils.isEmpty(alias))
		{
			//如果简称为空，取对象简称的首字母小写开头
			String simpleName=parameter.getParameterType().getSimpleName();
			alias=simpleName.substring(0, 1).toLowerCase()+simpleName.substring(1);
		}
		return alias;
	}

	
}
