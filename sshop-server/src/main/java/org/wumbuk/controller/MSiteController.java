package org.wumbuk.controller;
import org.wumbuk.entity.Categories;
import org.wumbuk.entity.Good;
import org.wumbuk.entity.Msg;
import org.wumbuk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.util.List;
import java.util.Map;

/**
 * @auther PeihaoYang
 * @date 2020/12/8 - 20:04
 */

/**
 * mSite后端接口
 */
@Controller
@RequestMapping(value="msite",produces="application/json;charset=UTF-8")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class MSiteController {

    @Autowired
    CategoriesService categoriesService;

    @Autowired
    GoodService goodService;


    /**
     * 获取轮播图的商品信息
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "querycategories")
    @ResponseBody
    public Msg queryCategories(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("queryCategories方法执行了");
        Msg msg = new Msg();
        try {
            List<Categories> categories = categoriesService.getCategories();
            msg.setCode(0);
            msg.add("categorie",categories);
            System.out.println("得到的查询列表为"+categories.toString());
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
            msg.setCode(1);
            msg.setMsg("获取商品类别列表失败！！！");
            return msg;
        }
    }





    /**
     * 获取商品列表
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "querygoods")
    @ResponseBody
    public Msg queryGoods(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("queryGoods方法执行了");
        Msg msg = new Msg();

        try {
            List<Good> goods = goodService.getGoods();
            msg.setCode(0);
            msg.add("goods",goods);
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
            msg.setCode(1);
            msg.setMsg("服务器故障！");
            return  msg;
        }

    }





    @RequestMapping(value = "querygoodsvague")
    @ResponseBody
    public Msg queryGoodsVague(@RequestBody Map map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(" queryGoodsVague 方法执行了");
        String keyword= (String) map.get("keyword");
        System.out.println("得到的查询关键字为"+keyword);
        Msg msg = new Msg();

        try {
            List<Good> searchGoods = goodService.getGoodsVague(keyword);
            msg.setCode(0);
            msg.add("searchGoods",searchGoods);
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
            msg.setCode(1);
            msg.setMsg("服务器故障！");
            return  msg;
        }

    }











}
