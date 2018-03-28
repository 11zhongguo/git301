package cn.itcast.core.controller;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.core.pojo.User;
import cn.itcast.core.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    //进入添加页面
    @RequestMapping(value="list.action")
    public String list(){
        
        
        return "insert";
        
    }
    
    //保存
    @RequestMapping(value="insert.action")
    public void insert(User user){
        userService.saveUser(user);
    }
    @RequestMapping(value="show.action")
    public String show(User user,Model model) throws Exception{
        
        String baseURL = "http://localhost:8080/solr";
        SolrServer solrServer = new HttpSolrServer(baseURL);
        SolrQuery params = new SolrQuery();
        //查询所有
//        params.set("q", "*:*");
        params.setQuery("birthday:*");
        //执行查询
        QueryResponse response = solrServer.query(params);
        SolrDocumentList userList = response.getResults();
        //总条数
        long numFound = userList.getNumFound();
        System.out.println("总条数"+numFound);
        
        for (SolrDocument doc : userList) {
            System.out.println("id"+doc.get("id"));
            System.out.println("Name:" + doc.get("name"));
        }
        model.addAttribute("userList", userList);
        return "list";
    }
}
