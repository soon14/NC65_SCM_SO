/**
 * 
 */
package nc.ui.so.m30.billui.config;

import nc.bs.framework.codesync.client.NCClassLoader;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author gdsjw
 *
 */
public class DefaultBillConfig implements IBillConfig {

  private static String handlerconfigfile =
      "nc/ui/so/m30/config/saleorder_handler.xml";

  private static String relationconfigfile =
      "nc/ui/so/m30/config/saleorder_relation.xml";

  private static String elementconfigfile =
      "nc/ui/so/m30/config/saleorder_element.xml";

  private IElementItemConfig elementconfig;

  private IElementEventHandlerConfig handlerconfig;

  private IElementRelationConfig relationconfig;

  public DefaultBillConfig() {
    this.readElementconfig();
    this.readHandlerconfig();
    this.readRelationconfig();
  }

  protected void readHandlerconfig() {
    ListableBeanFactory factory = new DefaultListableBeanFactory();
    XmlBeanDefinitionReader reader =
        new XmlBeanDefinitionReader((BeanDefinitionRegistry) factory);
    ((AbstractBeanFactory) factory).setBeanClassLoader(NCClassLoader
        .getNCClassLoader());

    Resource resource =
        new ClassPathResource(DefaultBillConfig.handlerconfigfile,
            NCClassLoader.getNCClassLoader());
    reader.loadBeanDefinitions(resource);

    this.handlerconfig =
        (IElementEventHandlerConfig) factory.getBean("handlerconfig");
  }

  protected void readRelationconfig() {
    ListableBeanFactory factory = new DefaultListableBeanFactory();
    XmlBeanDefinitionReader reader =
        new XmlBeanDefinitionReader((BeanDefinitionRegistry) factory);
    ((AbstractBeanFactory) factory).setBeanClassLoader(NCClassLoader
        .getNCClassLoader());

    Resource resource =
        new ClassPathResource(DefaultBillConfig.relationconfigfile,
            NCClassLoader.getNCClassLoader());
    reader.loadBeanDefinitions(resource);

    this.relationconfig =
        (IElementRelationConfig) factory.getBean("relationconfig");
  }

  protected void readElementconfig() {
    ListableBeanFactory factory = new DefaultListableBeanFactory();
    XmlBeanDefinitionReader reader =
        new XmlBeanDefinitionReader((BeanDefinitionRegistry) factory);
    ((AbstractBeanFactory) factory).setBeanClassLoader(NCClassLoader
        .getNCClassLoader());

    Resource resource =
        new ClassPathResource(DefaultBillConfig.elementconfigfile,
            NCClassLoader.getNCClassLoader());
    reader.loadBeanDefinitions(resource);

    this.elementconfig = (IElementItemConfig) factory.getBean("elementconfig");
  }

  @Override
  public IElementEventHandlerConfig getElementEventHandlerConfig() {
    return this.handlerconfig;
  }

  @Override
  public IElementRelationConfig getElementRelationConfig() {
    return this.relationconfig;
  }

  @Override
  public IElementItemConfig getElementItemConfig() {
    return this.elementconfig;
  }

}
