package {{path}}.{{entity.name}}.routes;

import {{path}}.base.routes.BaseApiRoutes;

public class {{entity.nameUpper}}ApiRoutes {
    public static final String ROOT = BaseApiRoutes.v1 + "/{{entity.restApiUrl}}";
    public static final String BY_ID = ROOT + "/{id}";
}
