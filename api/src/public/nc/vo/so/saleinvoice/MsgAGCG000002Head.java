package nc.vo.so.saleinvoice;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by shangguoqiang on 2017-03-03 15:25:39
 */
@XmlRootElement(name = "Message")//攀钢采购系统发票主信息头
public class MsgAGCG000002Head {
    String msgId;              //消息ID
    String resourceId;         //ResourceID
    List<MsgAGCG000002> msgBody;    //所有消息

    @XmlElement(name = "DataRow")
    public List<MsgAGCG000002> getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(List<MsgAGCG000002> msgBody) {
        this.msgBody = msgBody;
    }

    @XmlAttribute(name = "MsgID")
    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @XmlAttribute(name = "ResourceID")
    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

}
