package nk;

import kd.bos.algo.DataSet;
import kd.bos.dataentity.entity.DynamicObjectCollection;
import kd.bos.db.DB;
import kd.bos.db.DBRoute;
import kd.bos.entity.report.CellStyle;
import kd.bos.form.events.BeforeCreateListColumnsArgs;
import kd.bos.list.BillList;
import kd.bos.list.IListColumn;
import kd.bos.list.plugin.AbstractListPlugin;
import kd.bos.orm.ORM;
import kd.bos.servicehelper.QueryServiceHelper;

import java.util.ArrayList;
import java.util.List;
/*
* 单据状态:billstatus
* 登记单号:billno
* 表单标识：qyrv_registerbill
*
* */
public class ChangeLineColor  extends AbstractListPlugin {
    @Override
    public void beforeCreateListColumns(BeforeCreateListColumnsArgs e) {
        super.beforeCreateListColumns(e);
        List<IListColumn> listColumns = e.getListColumns();
        List<CellStyle> cellStyles = new ArrayList<>();
        BillList bl= this.getControl("billlistap");
        DynamicObjectCollection dCollection = QueryServiceHelper.query(
                "qyrv_registerbill",
                "billno,billstatus",
                null,
                "billno asc"
        );
        for(int x = 0;x<dCollection.size();x++){
            String billstatus = (String)dCollection.get(x).get("billstatus");
            IListColumn listColumn = listColumns.get(x);
            for(IListColumn lc:listColumns){
                switch (billstatus) {
                    case "A": {
                        CellStyle cs = new CellStyle();
                        cs.setFieldKey(lc.getListFieldKey().replace(".", "_"));
                        cs.setBackColor("White");
                        cs.setForeColor("Black");
                        cs.setRow(x);
                        cellStyles.add(cs);
                        break;
                    }
                    case "B": {
                        CellStyle cs = new CellStyle();
                        cs.setFieldKey(lc.getListFieldKey().replace(".", "_"));
                        cs.setBackColor("Red");
                        cs.setForeColor("White");
                        cs.setRow(x);
                        cellStyles.add(cs);
                        break;
                    }
                    case "C": {
                        CellStyle cs = new CellStyle();
                        cs.setFieldKey(lc.getListFieldKey().replace(".", "_"));
                        cs.setBackColor("Green");
                        cs.setForeColor("White");
                        cs.setRow(x);
                        cellStyles.add(cs);
                        break;
                    }
            }

            }
        }
        bl.setCellStyle(cellStyles);
    }
}
