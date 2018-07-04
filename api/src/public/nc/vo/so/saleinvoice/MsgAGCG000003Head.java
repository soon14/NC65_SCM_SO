package nc.vo.so.saleinvoice;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by shangguoqiang on 2017-03-03 15:26:05
 */
@XmlRootElement(name = "Message")//攀钢采购系统发票明细头
public class MsgAGCG000003Head {
    String msgId;              //消息ID
    String resourceId;         //ResourceID
    List<MsgAGCG000003> msgBody;    //所有消息

    @XmlElement(name = "DataRow")
    public List<MsgAGCG000003> getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(List<MsgAGCG000003> msgBody) {
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
