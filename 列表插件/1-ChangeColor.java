package nk;


import kd.bos.dataentity.entity.DynamicObjectCollection;
import kd.bos.entity.datamodel.ListSelectedRow;
import kd.bos.entity.datamodel.ListSelectedRowCollection;
import kd.bos.entity.report.CellStyle;
import kd.bos.form.events.BeforeCreateListColumnsArgs;
import kd.bos.list.BillList;
import kd.bos.list.IListColumn;
import kd.bos.list.QueryBuilderSelectFields;
import kd.bos.list.plugin.AbstractListPlugin;
import kd.bos.servicehelper.QueryServiceHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
/*
 * 单据状态:billstatus
 * 登记单号:billno
 * 物品名称：qyrv_material.name
 * 物品分类：qyrv_material.group.name
 * 库存数量：qyrv_qty
 * 表单标识：qyrv_deposit
 *
 * */
public class ChangeColor extends AbstractListPlugin {
    @Override
    public void beforeCreateListColumns(BeforeCreateListColumnsArgs e) {
        super.beforeCreateListColumns(e);
        List<IListColumn> listColumns = e.getListColumns();
        List<CellStyle> cellStyles = new ArrayList<>();
        BillList bl= this.getControl("billlistap");
        DynamicObjectCollection dCollection= QueryServiceHelper.query(
                "qyrv_deposit", "billno,qyrv_material.name,qyrv_material.group.name,qyrv_qty", null,"billno desc");
        for(int x = 0;x<dCollection.size();x++) {
            BigDecimal qty = (BigDecimal)dCollection.get(x).get("qyrv_qty");
            if(qty.compareTo(BigDecimal.ZERO)>0&&qty.compareTo(BigDecimal.TEN)<0) {
                for(IListColumn listColumn : listColumns) {
                    CellStyle cellStyle = new CellStyle();
                    cellStyle.setFieldKey("qyrv_qty");
                    cellStyle.setBackColor("Red");
                    cellStyle.setRow(x);
                    cellStyles.add(cellStyle);
                }
            } else if (qty.compareTo(BigDecimal.ZERO)==0) {
                for(IListColumn listColumn : listColumns) {
                    CellStyle cellStyle = new CellStyle();
                    cellStyle.setFieldKey("qyrv_qty");
                    cellStyle.setBackColor("Yellow");
                    cellStyle.setRow(x);
                    cellStyles.add(cellStyle);
                }
            }
        }
        bl.setCellStyle(cellStyles);
    }

}
