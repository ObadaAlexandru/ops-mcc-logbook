package de.tum.moveii.ops.logbook.api.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by alexandruo
 */
@Controller
@RequestMapping("/swagger-ui")
public class SwaggerController {
    private static final String SWAGGER_RESOURCE_MAPPING = "swagger";
    private String specName;

    public SwaggerController(@Value("${moveii.swagger.spec.name:swagger.yml}") String specName) {
        this.specName = specName;
    }

    /**
     * Redirects to the swagger-ui
     * References the swagger spec in the static content
     * @return swagger index
     */
    @RequestMapping(method = RequestMethod.GET)
    public String swaggerUi() {
        return String.format("redirect:/%s/ui/index.html?url=%s", SWAGGER_RESOURCE_MAPPING, getUrlParameter());
    }

    private String getUrlParameter() {
        return String.format("/%s/spec/%s", SWAGGER_RESOURCE_MAPPING, specName);
    }
}
