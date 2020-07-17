package org.tron.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-17T15:25:41.238219+08:00[Asia/Shanghai]")

@Controller
@RequestMapping("${openapi.rosetta.base-path:}")
public class NetworkApiController implements NetworkApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public NetworkApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
