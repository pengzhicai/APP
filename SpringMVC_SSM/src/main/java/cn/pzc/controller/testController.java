package cn.pzc.controller;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mi on 18-10-10.
 */
@Controller
@RequestMapping("/test")
public class testController {

    @RequestMapping("/createJira")
    @ResponseBody
    JSONObject createJira(
            @RequestParam(required = false) String infoId,
            @RequestParam(required = false) String owner,
            HttpServletRequest request, HttpServletResponse response) {

        JSONObject retJson = new JSONObject();
        retJson.put("312",3213);
        return retJson;
    }
}
