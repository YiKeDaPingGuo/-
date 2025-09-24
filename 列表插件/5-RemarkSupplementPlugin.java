package nk;

import kd.bos.dataentity.entity.DynamicObject;
import kd.bos.dataentity.entity.DynamicObjectCollection;
import kd.bos.form.events.BeforeCreateListColumnsArgs;
import kd.bos.form.events.BeforeCreateListDataProviderArgs;
import kd.bos.list.plugin.AbstractListPlugin;
import kd.bos.mvc.list.ListDataProvider;
/*
* 备注：qyrv_remark
*
* */
public class RemarkSupplementPlugin extends AbstractListPlugin {
    @Override
    public void beforeCreateListDataProvider(BeforeCreateListDataProviderArgs e){
        e.setListDataProvider(new MyListDataProvider());

    }

}
class MyListDataProvider extends ListDataProvider {
    @Override
    public DynamicObjectCollection getData(int start,int limit){
        DynamicObjectCollection rows = super.getData(start,limit);
        if(rows.isEmpty()
        ||!rows.getDynamicObjectType().getProperties().containsKey("qyrv_remark")){
            return rows;
        }
        for(DynamicObject row:rows){
            row.set("qyrv_remark","登记单备注...");
        }
        return rows;
    }
}
