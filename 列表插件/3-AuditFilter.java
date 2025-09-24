package nk;

import kd.bos.dataentity.entity.DynamicObjectCollection;
import kd.bos.entity.report.CellStyle;
import kd.bos.form.events.BeforeCreateListColumnsArgs;
import kd.bos.form.events.SetFilterEvent;
import kd.bos.list.BillList;
import kd.bos.list.IListColumn;
import kd.bos.list.plugin.AbstractListPlugin;
import kd.bos.orm.query.QCP;
import kd.bos.orm.query.QFilter;
import kd.bos.plugin.sample.bill.list.template.SetFilter;
import kd.bos.servicehelper.QueryServiceHelper;

import java.util.ArrayList;
import java.util.List;

public class AuditFilter extends AbstractListPlugin {
    @Override
    public void setFilter(SetFilterEvent e) {
        super.setFilter(e);
        e.addCustomQFilter(new QFilter("billstatus", QCP.equals,"C"));
    }
//    @Override
//    public void beforeCreateListColumns(BeforeCreateListColumnsArgs e) {
//        super.beforeCreateListColumns(e);
//        List<IListColumn> listColumns = e.getListColumns();
//        List<CellStyle> cellStyles = new ArrayList<>();
//        BillList bl= this.getControl("billlistap");
//        DynamicObjectCollection dCollection= QueryServiceHelper.query(
//                "qyrv_registerbill",
//                "billno," +
//                        "billstatus"
//                , null,"billno asc");
//    }
}
