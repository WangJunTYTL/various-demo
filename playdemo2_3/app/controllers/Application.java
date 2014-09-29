package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Application extends Controller {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static Result index() {
        //testDo();
        //testJson();
        return ok(index.render("wo shi ni da ye da da ye ."));
    }



    public static Result routesDemo2(String arg) {
        return ok("arg="+arg);
    }
    public static Result routesDemo3(String arg) {
        return ok("arg="+arg);
    }

    public static Result routesDemo5(String arg) {
        return ok("arg="+arg);
    }



    private static void testDo() {
        List<String> list = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        List<String> list3 = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list2.add("d");
        list2.add("e");
        list2.add("f");
        int i = list.size() - 1, j, k;
        String a = "", b = "", c = "";
        do {
            if (list.size() != 0 && i >= 0)
                a = list.get(i);
            i--;
            System.out.println("-----------------------");
            j = list2.size() - 1;
            do {
                if (list2.size() != 0 && j >= 0)
                    b = list2.get(j);
                j--;
                k = list3.size() - 1;
                do {
                    if (list3.size() != 0 && k >= 0)
                        c = list3.get(k);
                    System.out.println(a + b + c);
                    k--;
                } while (k >= 0);
            } while (j >= 0);
        } while (i >= 0);
    }

    private static void testJson() {
        String json1 = "[\"hello\"]";
        JsonNode jsonNode = Json.parse(json1);
        Json.stringify(jsonNode);
        System.out.println(jsonNode.toString());
        System.out.println(Json.stringify(jsonNode));
        ObjectNode jsonObject = Json.newObject();
        jsonObject.put("hello", jsonNode);
        jsonObject.put("hello", 56);
        System.out.println(jsonObject.toString());
        List<Object> strings = new ArrayList<Object>();
        strings.add("hello");
        strings.add("world");
        jsonObject.put("test", Json.toJson(strings));
        System.out.println(jsonObject.toString());
        jsonObject.put("test2", Json.toJson("string"));
        jsonObject.put("test2", Json.toJson("sshshhs"));
        List<String> list = new ArrayList<String>();
        list.add("hello me");
        //ObjectNode objectNode = Json.newObject();

        jsonObject.put("me", Json.toJson(list));
        System.out.println(jsonObject.toString());
        System.out.println(jsonObject.size());
        System.out.println(jsonObject.get("test").isArray());
        JsonNode jsonNode1 = jsonObject.get("test");
        Iterator iterator = jsonNode1.iterator();
        while ((iterator.hasNext())) {
            System.out.println(iterator.next());
        }
        Iterator<JsonNode> iterator1 = jsonObject.iterator();
        while (iterator1.hasNext()) {
            System.out.println("----------------------");
            JsonNode jsonNode2 = iterator1.next();
            if (jsonNode2.isArray()) {
                Iterator iterator2 = jsonNode2.iterator();
                while ((iterator2.hasNext())) {
                    System.out.println(iterator2.next());
                }
            } else {
                if (jsonNode2.isTextual()) {
                    System.out.println("String:" + jsonNode2);
                }
            }

        }
    }

}