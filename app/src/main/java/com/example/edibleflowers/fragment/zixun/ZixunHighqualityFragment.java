package com.example.edibleflowers.fragment.zixun;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.edibleflowers.R;
import com.example.edibleflowers.binder.ZixunHighqualityBinder;
import com.example.edibleflowers.binder.ZixunNewsBinder;
import com.example.edibleflowers.item.ZixunHighqualityItem;
import com.example.edibleflowers.item.ZixunNewsItem;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ZixunHighqualityFragment#newInstance} factory method to
 * create an instance of this fragment.
 * @author 65667
 */
public class ZixunHighqualityFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View root;

    private RecyclerView recyclerView;
    private MultiTypeAdapter adapter;

    private Items mItems;


    public ZixunHighqualityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ZixunHighqualityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ZixunHighqualityFragment newInstance(String param1, String param2) {
        ZixunHighqualityFragment fragment = new ZixunHighqualityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_zixun_highquality, container, false);
        initView();
        return root;
    }

    private void initView() {
        List<String> name = new ArrayList<>();
        List<String> introduce = new ArrayList<>();
        List<String> value = new ArrayList<>();
        List<String> part = new ArrayList<>();

        recyclerView = root.findViewById(R.id.ev_high_quality);
        adapter = new MultiTypeAdapter();
        adapter.register(ZixunHighqualityItem.class, new ZixunHighqualityBinder());
        recyclerView.setAdapter(adapter);

        //[{"fintroduce":"\"丁香属于木犀科、丁香属，拉丁名是Syringa oblata ，属落叶灌木或小乔木。广泛分布于桑给巴尔、马达加斯加岛等地，因花筒细长如钉且香故名。为哈尔滨市市花，是著名的庭园花木。花序硕大、开花繁茂，花色淡雅、芳香，习性强健，栽培简易，因而在园林中广泛栽培应用。花两性，呈顶生或侧生的圆锥花序，花色以白色和紫色为居多。由其制成的丁香油、丁香酚有良好的药用价值。古代诗人多以丁香写愁。因为丁香花多成簇开放，好似结。称之为“丁结，百结花”。\",\r\n    \"丁香是由未开放的花蕾芽，经干燥而制得，状似圆头钉子，其香味浓烈，口感苦，烹饪之后变得温和。丁香属一种芳香健胃剂，能促使胃液分泌，增强胃肠蠕动。\",\r\n    \"在法国，“丁香花开的时候”意指气候最好的时候。生日是5月17日或者6月12日的人的幸运花是丁香花。在西方，该花象征着“年轻人纯真无邪，初恋和谦逊”。\"","fname":"丁香花","fno":1,"fpart":"\"有温中、暖肾、降逆。温中降逆；温肾助阳之功能。主治：治呃逆、呕吐、反胃、痢疾、心腹冷痛、痃癖、疝气、癣症。\",\r\n    \"药材基源：为双子叶植物药桃金娘科植物丁香的花蕾。\",\r\n    \"丁香水提取物灌胃，对小鼠水浸应激性溃疡和大鼠盐酸胃溃疡有明显抑制作用；醚提取物灌胃，可明显抑制消炎痛加乙醇诱发的小鼠胃溃疡及盐酸引起的大鼠胃溃疡。丁香水煎剂灌胃能显著抑制小鼠的胃排空及未尝墨汁推进率；醚提取物灌胃能抑制蓖麻油引起的小鼠腹泻；水提取物灌胃能明显抑制番泻叶引起的小鼠腹泻．但亦有报告认为，水煎剂对两种腹泻模型仅有对抗倾向而无明显影响。\",\r\n    \"丁香醚提取物有明显促进麻醉大鼠胆汁分泌的作用，并可维持2小时。\",\r\n    \"丁香油能有效抑制由花生四烯酸（AA）、胶原和肾上腺素诱发的血小板聚集，尤其对AA诱发的聚集抑制最强．丁香油的抑制聚集过程似乎是通过减少血小板凝集素（TXB2）形成的。\",\r\n    \"丁香醚提取物或水提取物给小鼠灌胃，均可显著延长小鼠痛觉反应潜伏期（热板法）或显著减少醋酸刺激引起的扭体反应次数。\"","fvalue":"\"丁香属植物主要应用于园林观赏，因其具有独特的芳香、硕大繁茂之花序、优雅而调和的花色、丰满而秀丽的姿态，在观赏花木中早已享有盛名，已成为全世界园林中不可缺少的花木。\",\r\n    \"可丛植于路边、草坪或向阳坡地，或与其他花木搭配栽植在林缘，也可在庭前、窗外孤植，或将各种丁香穿插配植，布置成丁香专类园。\",\r\n    \"还宜盆栽，并是切花插瓶的良好材料。丁香对二氧化硫及氟化氢等多种有毒气体，都有较强的抗性，故又是工矿区等绿化、美化的良好材料。\""},{"fintroduce":"\"三色堇（学名：Viola tricolor L.），在欧洲是常见的野花，也常栽培于公园中。三色菫以露天栽种为宜，无论花坛、庭园、盆栽皆适合。不适合种于室内，因为光线不足，生长会迟缓，枝叶无法充分茁壮，导致无法开花，开花后也不应移入室内，以长保花朵寿命。\",\r\n    \"三色堇为冰岛原产花卉，定为国花。但三色堇还是波兰国花。 \"","fname":"三色堇","fno":2,"fpart":"\"三色堇可杀菌、治疗皮肤上青春痘、粉刺、过敏问题。在中国医药古籍记载的护肤圣品中，三色堇无疑是最炫目的。三国时期的《名医别录》中就已把三色堇列为重要护肤药材，隋炀帝为讨后宫佳丽的欢心，曾组织太医研究三色堇祛痘的多种方法，并将其一一写进《隋炀帝后宫诸宫药方》与《香方粉泽》等书之中。中医圣典《本草纲目》更是详细内载三色堇的神奇去痘功效：“三色堇，性表温和，其味芳香，引药上行于面，去疮除疤，疮疡消肿。” 三色堇全草，可以用作药物，茎叶含三色堇素，主治咳嗽等疾病。\",\r\n    \"治咳嗽：三色堇3-9克，水煎服。(《药用花卉》)\",\r\n    \"治小儿瘰疬(未破者)：鲜三色堇花捣汁涂患处。(《药用花卉》)\",\r\n    \"三色堇具有杀菌消炎、祛痘和防过敏的功效。\",\r\n    \"喝三色堇茶，然后用三色堇茶涂抹在痤疮患处，长期坚持，对痘痘、痘印有很好的疗效。\"","fvalue":"\"三色堇是布置春季花坛的主要花卉之一。\",\r\n    \"因花有三种颜色对称地分布在五个花瓣上，构成的图案，形同猫的两耳、两颊和一张嘴，故又名猫儿脸。又因整个花被风吹动时，如翻飞的蝴蝶，所以又有蝴蝶花的别名。经自然杂交和人工选育，三色堇花的色彩、品种比较繁多。除一花三色者外，还有纯白、纯黄、纯紫、紫黑等。另外，还有黄紫、白黑相配及紫、红、蓝、黄、白多彩的混合色等。从花形上看，有大花形、花瓣边缘呈波浪形的及重瓣形的。\",\r\n    \"三色堇是冬、春季节优良的花坛材料，因为适应性强、耐粗放型管理，可以盆栽供人们欣赏。\",\r\n    \"花深紫色，具有芳香味，可提取香精。\",\r\n    \"三色堇是西方人代表想念的花。\",\r\n    \"三色堇可用于室内盆栽、花坛花卉、花境花卉。观花类，花坛、花径、镶边 。\""},{"fintroduce":"\"诸葛菜（二月兰），十字花科诸葛菜属，一年或二年生草本。因农历二月前后开始开蓝紫色花，故称二月兰。生长于平原、山地、路旁、地边。对土壤光照等条件要求较低，耐寒旱，生命力顽强。传说诸葛亮率军出征时曾采嫩梢为菜，故得名。\"","fname":"诸葛菜","fno":3,"fpart":"\"诸葛菜可以降低体内的胆固醇含量，也可以清理血管，达到软化血管的作用。这样的效果是可以避免血栓的形成，降低了一些心脑血管疾病发病的概率。所以一些有三高的人群或者说中老年等心脑血管疾病的高发人群可以经常食用诸葛菜，可以起到保养健康的作用。\"","fvalue":"\"诸葛菜的全身都是宝，几乎都是可以食用的，特别是它的种子，可以用来榨油，是菜油的原始材料之一，在油价日益上涨的今天，色拉油的竞争力也慢慢在弱化，从健康和省钱两方面考虑，菜油在将来肯定会异军突起。\",\r\n    \"诸葛菜为早春常见野菜，其嫩茎叶生长量较大，营 养丰富。据测定，每100克鲜品中含胡萝卜素3.32毫克、维生素B20.16毫克、维生素C59毫克。种子含油量高达50%以上，又是很好的油料植物。特别是其亚油酸比例较高，对人体极为有利。因亚油酸具有降低人体内血清胆固醇和甘油三酯的功能，并可软化血管和阻止血栓形成，是心血管病患者的良好药物。\",\r\n    \"诸葛菜耐寒性较强，萌发早，可调节早春蔬菜淡季。其野外采集一般在3-4月份进行。采后只需用开水焯一下，去掉苦味即可食用。\","}]

        OkGo.<String>post("http://10.132.41.48:9596/showFlower")
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("response", response.body());
                        mItems = new Items();
                        response.toString();
                        JSONArray jsonArray = JSON.parseArray(response.body());
                        for (int i=0; i<jsonArray.size(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            name.add(jsonObject.getString("fname"));
                            introduce.add(jsonObject.getString("fintroduce"));
                            value.add(jsonObject.getString("fvalue"));
                            part.add(jsonObject.getString("fpart"));
                        }
                        for (int i=0; i<name.size(); i++) {
                            ZixunHighqualityItem zixunHighqualityItem = new ZixunHighqualityItem();
                            zixunHighqualityItem.setName(name.get(i));
                            zixunHighqualityItem.setIntroduce(introduce.get(i));
                            zixunHighqualityItem.setValue(value.get(i));
                            zixunHighqualityItem.setPart(part.get(i));
                            mItems.add(zixunHighqualityItem);
                        }
                        adapter.setItems(mItems);
                        adapter.notifyDataSetChanged();
                    }
                });

    }
}