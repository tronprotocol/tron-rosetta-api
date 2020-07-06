import com.google.inject.AbstractModule;

import controllers.*;

public class Module extends AbstractModule {

    @Override
    protected void configure() {
        bind(AccountApiControllerImpInterface.class).to(AccountApiControllerImp.class);
        bind(BlockApiControllerImpInterface.class).to(BlockApiControllerImp.class);
        bind(ConstructionApiControllerImpInterface.class).to(ConstructionApiControllerImp.class);
        bind(MempoolApiControllerImpInterface.class).to(MempoolApiControllerImp.class);
        bind(NetworkApiControllerImpInterface.class).to(NetworkApiControllerImp.class);
    }
}