package nk;

import com.kingdee.cosmic.ctrl.swing.StringUtils;
import kd.bos.bill.BillShowParameter;
import kd.bos.bill.OperationStatus;
import kd.bos.form.ShowType;
import kd.bos.form.events.HyperLinkClickArgs;
import kd.bos.list.plugin.AbstractListPlugin;
/*
* 物品分类超链接：qyrv_materialtype.name（注意把“.”改成“_”）
*
* */
public class MaterialTypeReqPlugin extends AbstractListPlugin {
    @Override
    public void billListHyperLinkClick(HyperLinkClickArgs e){
        super.billListHyperLinkClick(e);
        if(StringUtils.equals("qyrv_materialtype_name",e.getHyperLinkClickEvent().getFieldName())){
            e.setCancel(true);
            BillShowParameter param = new BillShowParameter();
            param.setFormId("qyrv_materialtype");
            param.getOpenStyle().setShowType(ShowType.Modal);
            param.setStatus(OperationStatus.ADDNEW);
            this.getView().showForm(param);
        }
    }
}
