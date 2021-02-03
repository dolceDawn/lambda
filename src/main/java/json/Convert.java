package json;

import com.alibaba.fastjson.JSON;

import java.util.List;

public class Convert {

    public static void main(String[] args) {

        String sampleTime = "[{\"start\":1600674315050,\"end\":1600760715050}, {\"start\":1600674315051,\"end\":1600760715051}]";

        List<SampleTime> twSystems = JSON.parseArray(sampleTime, SampleTime.class);


        System.out.println(JSON.toJSON(twSystems));

    }

}
