import com.google.gson.Gson;

import java.util.*;

/**
 * @Desc:
 * @Author: zhangliming
 * @Date: 9/10/2020 18:16
 */
public class ScreenTreeCopy {

    public static void main(String[] args) {

        ScreenTree root = new ScreenTree("DPP_01_40001119","集团-0903test",null, "CORPORATE");

        ScreenTree a1 = new ScreenTree("DPP_01_40001120","分公司","DPP_01_40001119", "BRANCH");

        List<ScreenTree> c1 = new ArrayList<>();
        c1.add(a1);
        root.setChilds(c1);

        ScreenTree a2 = new ScreenTree("DPP_01_40001121","电厂0910","DPP_01_40001120", "POWERPLANT");
        List<ScreenTree> c2 = new ArrayList<>();
        c2.add(a2);
        a1.setChilds(c2);

        ScreenTree a3 = new ScreenTree("DPP_01_40001122","机组2","DPP_01_40001121", "CREW");
        List<ScreenTree> c3 = new ArrayList<>();
        c3.add(a3);
        a2.setChilds(c3);

        ScreenTree a4 = new ScreenTree("DPP_01_40001123","设备1","DPP_01_40001122", "ASSET");
        List<ScreenTree> c4 = new ArrayList<>();
        c4.add(a4);
        a3.setChilds(c4);

        HashMap<String, String> resourceOptMap = new HashMap<>();
        resourceOptMap.put("DPP_01_40001121", "DPP_01_40001121");
        resourceOptMap.put("DPP_01_40001122", "DPP_01_40001122");

        //deep copy
//        ScreenTree copyRoot = (ScreenTree) root.clone();
//        deepCopy(copyRoot);
//
//        filterPermission(copyRoot, resourceOptMap);


        System.out.println(root);


    }


    public static void deepCopy(ScreenTree tree) {

        List<ScreenTree> childs = tree.getChilds();

        if (childs == null || childs.size() == 0) {
            return;
        }

        List<ScreenTree> copyChilds = new ArrayList<>();

        for (ScreenTree c : childs) {
            ScreenTree cc = (ScreenTree) c.clone();
            copyChilds.add(cc);
            deepCopy(c);
        }

        tree.setChilds(copyChilds);

    }


    public static void filterPermission(ScreenTree tree, HashMap<String, String> resourceOptMap) {

        List<ScreenTree> childs = tree.getChilds();

        if (childs == null || childs.size() == 0) {
            return;
        }

        Iterator<ScreenTree> childIterator = childs.iterator();

        while (childIterator.hasNext()) {

            ScreenTree node = childIterator.next();

            if (!resourceOptMap.containsKey(node.getId()) && !node.getTypecode().equals(NodeTypeEnum.CORPORATE.getType())
                    && !node.getTypecode().equals(NodeTypeEnum.BRANCH.getType())) {
                childIterator.remove();
            }

            if (node.getChilds() != null && node.getChilds().size() > 0) {
                filterPermission(node, resourceOptMap);
            }
        }

    }

}
