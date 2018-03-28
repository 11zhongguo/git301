package cn.itcast.core.controller;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

public class Demo {

    @Test
    public void testQuery() throws Exception{
        String baseURL = "http://localhost:8080/solr";
        SolrServer solrServer = new HttpSolrServer(baseURL);
        SolrQuery params = new SolrQuery();
        //查询所有
//        params.set("q", "*:*");
        params.setQuery("*:*");
        //执行查询
        QueryResponse response = solrServer.query(params);
        SolrDocumentList docs = response.getResults();
        //总条数
        long numFound = docs.getNumFound();
        System.out.println("总条数"+numFound);
        
        for (SolrDocument doc : docs) {
            System.out.println("id"+doc.get("id"));
            System.out.println("Name:" + doc.get("name"));
        }
        
    }
}
