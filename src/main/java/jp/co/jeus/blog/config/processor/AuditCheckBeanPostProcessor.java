package jp.co.jeus.blog.config.processor;

import jp.co.jeus.blog.properties.FilePathProperty;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Check bean Processor
 */
@Log4j2
@Component
public class AuditCheckBeanPostProcessor implements BeanPostProcessor {

    /**
     * Before initialization
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof JpaRepository) {
            log.debug("In AuditCheckBeanPostProcessor.postProcessBeforeInitialization," + "processing bean type:" + bean.getClass());
        }
        return bean;
    }

    /**
     * After initialization
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof JpaRepository) {
            log.debug("In AuditCheckBeanPostProcessor.postProcessAfterInitialization," + "processing bean type:" + bean.getClass());
        }
        if (bean instanceof FilePathProperty) {
            log.debug("In AuditCheckBeanPostProcessor.postProcessAfterInitialization," + "processing bean type:" + bean.getClass());
        }
        if (bean instanceof TaskExecutor) {
            log.debug("In AuditCheckBeanPostProcessor.postProcessAfterInitialization," + "processing bean type:" + bean.getClass());
        }
        return bean;
    }
}
