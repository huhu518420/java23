package com.kaishengit.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Movie;
import com.kaishengit.service.MovieService;
import com.kaishengit.util.StringsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 业务控制器
 * Created by huhu5 on 2017/7/15.
 */
@Controller
@RequestMapping("/movie")
public class MovieController {

    //依赖与Service,所以要实现Service里面的接口
    //接口指向实现类，降低耦合性
    @Autowired
    private MovieService movieService;
    //MovieService movieService = new movieServieImp();

    /**
     * @param pageNo
     * @param title
     * @param daoyan
     * @param min
     * @param max
     * @param model
     * @return
     * 分页查询
     */
    //指定请求方式
    @GetMapping
    public String list(@RequestParam(required = false,defaultValue ="1",name = "p")Integer pageNo,
                       @RequestParam(required = false)String title,
                       @RequestParam(required = false)String daoyan,
                       @RequestParam(required = false)Float min,
                       @RequestParam(required = false)Float max,
                       Model model) {
        //对地址栏中的参数进行转码
        title = StringsUtil.isoToUtf8(title);
        daoyan = StringsUtil.isoToUtf8(daoyan);
        //@RequestParam获取页面（url里）参数值，name属性的值是页面href的变量名
        //使用Model对象给客服端做出响
        //model.addAttribute("movieList",movieService.findAll());
        /*System.out.println(movieService.findAll());*/
        //分页PageInfo是框架的对象
       // PageInfo<Movie> pageInfo = movieService.findByPageNo(pageNo);
        PageInfo<Movie> pageInfo = movieService.findByParam(title,daoyan,min,max,pageNo);
        model.addAttribute("title",title);
        model.addAttribute("page",pageInfo);
        model.addAttribute("daoyan",daoyan);
        model.addAttribute("min",min);
        model.addAttribute("max",max);

        return "movie/list";
    }

    //页面访问/new的get请求

    /**
     * 添加新电影
     * @return
     */
    @GetMapping("/new")
    public String newMovie() {

        //跳转到页面
        return "movie/new";
    }

    /**
     * 保存方法
     * @param movie
     * @param redirectAttributes
     * @return
     */
    //页面新增post请求来到此方法
    @PostMapping("/new")
    public String saveMovie(Movie movie, RedirectAttributes redirectAttributes) {

        movieService.save(movie);
        redirectAttributes.addFlashAttribute("message","添加成功");
        //重定向到另一个业务控制器方法
        return "redirect:/movie";
    }

    /**
     * 根据id删除
     * @return
     */
    @GetMapping("/{id:\\d+}/del")
    public String delMovieById(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        //添加@PathVariable获取表单传来的值
        System.out.println(id);
        movieService.del(id);
        //删除完成后给用户提示
        redirectAttributes.addFlashAttribute("message","删除成功");
        //返回list
        return "redirect:/movie";
    }

    /**
     * 根据id显示
     * 先根据id查找到对应的Movie对象。
     * 再将对象传递到jsp页面进行展示
     */
    @GetMapping("/{id:\\d+}/edit")
    public String editById(@PathVariable Integer id,Model model) {
        //1.根据Id查找到对应的Movie对象
        Movie movie = movieService.findById(id);
        //2.再把对象传入到jsp页面进行显示
        model.addAttribute("movie",movie);
        return "/movie/edit";
    }
    @PostMapping("/{id:\\d+}/edit")
    public String editMovie(Movie movie,RedirectAttributes redirectAttributes) {
        //获取表单提交传来的对象并调用Service的方法实现编辑
        System.out.println("发起post请求");
        movieService.editMovie(movie);

        //修改成功了给出提示
        redirectAttributes.addFlashAttribute("message","修改成功");
        //返回到list页面
        return "redirect:/movie";
    }



}
