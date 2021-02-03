import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.RandomAccess;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<Apple> apps = new ArrayList<>();

        Apple app1 = new Apple();
        Apple app2 = new Apple();
        Apple app3 = new Apple();
        Apple app4 = new Apple();
        Apple app5 = new Apple();
        Apple app6 = new Apple();
        Apple app7 = new Apple();
        Apple app8 = new Apple();
        Apple app9 = new Apple();
        Apple app10 = new Apple();
        Apple app11 = new Apple();

        apps.add(app1);
        apps.add(app2);
        apps.add(app3);
        apps.add(app4);
        apps.add(app5);
        apps.add(app6);
        apps.add(app7);
        apps.add(app8);
        apps.add(app9);
        apps.add(app10);
        apps.add(app11);

        PageRespone<Apple> pageRespone = new PageRespone<>();

        PageRequest pageRequest = new PageRequest();
        pageRequest.setPage(2);
        pageRequest.setPageSize(10);

        dealPageResponse(apps.size(), pageRequest, pageRespone);

        List<Apple> result = apps.stream().skip((pageRequest.getPage() - 1) * pageRequest.getPageSize())
                .limit(pageRequest.getPageSize()).collect(Collectors.toList());

        System.out.println(result);

        List<Apple> appleList2 = new ArrayList<>();

        appleList2.add(app1);

        String jstring = JSON.toJSONString(appleList2);

        List<Apple> list = JSON.parseArray (jstring,Apple.class);

        System.out.println(list);

    }


    public static <T> void dealPageResponse(int total, PageRequest pageRequest, PageRespone<T> pageRespone) {
        pageRespone.setTotal(total);
        Integer page = Integer.valueOf(pageRequest.getPage());
        Integer pageSize = Integer.valueOf(pageRequest.getPageSize());
        pageRespone.setPageSize(pageSize);
        int totalPage = (total % pageSize) == 0 ? total / pageSize : total / pageSize + 1;
        int current = totalPage > page ? page : totalPage;
        pageRespone.setCurrent(current);
    }

}
