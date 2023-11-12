package io.github.hizhangbo;

import io.github.hizhangbo.modal.ReceiptInfo;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    private static final Pattern pattern = Pattern.compile("(.*)'(.*?)', (0.\\d+).*");

    public static void main(String[] args) throws IOException {
//        String cmd = "paddleocr --image_dir ./huidan/3.jpg --use_gpu false";
//        String pwd = "D:\\Programs\\paddleocr\\ppocr_img";
//        Process processor = processor(cmd, pwd);
//        String result = strResults(processor);
//        String result = "回单交通银行资金汇划业务名称：汇入汇款解付入账回单编号：329795568032回单类型：借贷标志：贷方转账方式：实时转账凭证种类：凭证号码：主账号：付款人账号：23208840173170949174000通银KLYCHEVAAKJAGULSTRBAYRAMHANAPP16KERKI/LEBAP/TURKMENISTAN付款人名称：TUTCTM2XXXX西安光华路支行开户行名称：收款人账号：611301135018000478803会计业务章收款人名称：西安电子科技大学33F6907C开户行名称：交通银行西安光华路支行币种：CNY金额：17.414.60金额大写：人民币壹万柒仟肆佰壹拾肆圆陆角币种CNY金额17,414.60币种USD金额2.387.00牌价729.56000000兑换信息：摘要：IRK0611202307107附加信息：BILIM/4/TTB103101115061/CHINA/2023 24/TUITI0N FEE FOR JUMAGULYYEV MYRATPASS.NO A2067118 INV.N DD 31.08.2023中报号码：610100000563231024N015汇款人地址：KLYCHEVAAKJAGULSTRBAYRAMHANAPP16KERKI/LEBAP/TURKMENISTAN打印机构：01611701999打印柜员：会计流水号：EEJ0001040950312打印次数：1次记账日期：20231024复核柜员：授权柜员：经办柜员：记账柜员：EEJ0001记账机构：01611701999总张数：93当前第85张";
//        String result = "交通银行回单向单编号：329717024798回单类型：资金汇划业务名称：汇入汇款解付入账凭证种类：凭证号码：借贷标志：贷方转账方式：实时转账付款人账号：23208840153200602644000主账号：付款人名称：ASHYROVA GULNAHAL通银开户行名称：TNBKTM2XXXX行收款人账号：611301135018000478803西安光华路支行收款人名称：西安电子科技大学会计业务章开户行名称：交通银行西安光华路支行4D958D64币种：CNY金额：43.706.04金额大写：人民币肆万叁仟柒佰零陆圆零肆分兑换信息：币种USD金额5,990.00牌价729.65000000币种CNY金额43,706.04摘要：IRK0611202307177附加信息：INVOICE//17.09.2023TUITIONAND ACCOM.FEES CHARYYEV EZIZ PASSPORT:A2024686,2023-2024YEAR 申报号码：610100000563231024N006汇款人地址：STR.10HATARH.3FL.4ASHGABAT，TURKMENISTAN国外扣费：USD10.00打印次数：1次记账日期：20231024会计流水号：EEJ0001037894873打印机构：01611701999打印柜员：记账机构：01611701999经办柜员：记账柜员：EEJ0001复核柜员：授权柜员：批次号：0161170199920231025000007103总张数：93当前第66张";
        String result = "单交通银行回单编号：329657262862回单类型：资金汇划业务名称：汇入汇款解付入账凭证号码：借贷标志：贷方转账方式：实时转账凭证种类：付款人账号：23208840173170347844000主账号：付款人名称：HUDAYNAZAROVDAYANCHSTRMAGTYMGULYAPP39KERKI/LEBAP/TURKMENISTAN通银开户行名称：TUTCTM2XXXX收款人账号：611301135018000478803西安光华路支行收款人名称：西安电子科技大学会计业务章开户行名称：交通银行西安光华路支行8BFBBD38币种：CNY金额：25.189.64金额大写：人民币贰万伍仟壹佰捌拾玖圆陆角肆分币种USD金额3,447.00牌价730.77000000币种CNY金额25，189.64兑换信息：摘要：IRK0611202306593附加信息：YASJAY/4/TTB103092506581/CHINA/202324/ACC0MMODATI0NFEEFORVELIYEVA JENNETPASS.NOA1931166INV.NDD.17.08.2023 申报号码：610100000563231023N005汇款人地址：HUDAYNAZAROV DAYANCHSTRMAGTYMGULYAPP 39会计流水号：EEJ0001029625838打印机构：01611701999打印柜员：打印次数：1次记账日期：20231023经办柜员：记账柜员：EEJ0001复核柜员：授权柜员：记账机构：01611701999批次号：0161170199920231024000007054总张数：95当前第34张";
        String receiptNum = getReceiptNum(result);

        float money = getMoney(result);

        ReceiptInfo receiptInfo = getPassport(result);
        receiptInfo.setReceiptNum(receiptNum);
        receiptInfo.setMoney(money);

        System.out.println(receiptInfo);
    }

    public static Process processor(String cmd, String pwd) throws IOException {
        return Runtime.getRuntime()
                .exec(cmd, null, new File(pwd));
        //.exec("sh -c ls", null, new File("Pathname")); for non-Windows users
    }

    public static void printResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    public static String strResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
        String line = "";

        StringBuilder result = new StringBuilder();
        while ((line = reader.readLine()) != null) {

            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) {
                String text = matcher.group(2);
                result.append(text);
            }
        }
        return result.toString();
    }

    private static String getReceiptNum(String result) {
        Matcher matcher = Pattern.compile(".*单编号：(\\d{12}).*").matcher(result);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return "";
    }

    private static float getMoney(String result) {
        Matcher matcher = Pattern.compile(".*金额大写：人民币(.万)?(.仟|零)?(.佰|零)?(.拾|零)?(.)?圆(.角|零)?(.分)?.*").matcher(result);

        float money = 0f;
        if (matcher.matches()) {

            String wan = matcher.group(1);
            if (StringUtils.isNotBlank(wan) && wan.contains("万")) {
                char chi = wan.charAt(0);
                int num = convertChiUpperNum(chi);
                money += num * 10000;
            }

            String qian = matcher.group(2);
            if (StringUtils.isNotBlank(qian) && qian.contains("仟")) {
                char chi = qian.charAt(0);
                int num = convertChiUpperNum(chi);
                money += num * 1000;
            }

            String bai = matcher.group(3);
            if (StringUtils.isNotBlank(bai) && bai.contains("佰")) {
                char chi = bai.charAt(0);
                int num = convertChiUpperNum(chi);
                money += num * 100;
            }

            String shi = matcher.group(4);
            if (StringUtils.isNotBlank(shi) && shi.contains("拾")) {
                char chi = shi.charAt(0);
                int num = convertChiUpperNum(chi);
                money += num * 10;
            }

            String yuan = matcher.group(5);
            if (StringUtils.isNotBlank(yuan)) {
                char chi = yuan.charAt(0);
                int num = convertChiUpperNum(chi);
                money += num;
            }

            String jiao = matcher.group(6);
            if (StringUtils.isNotBlank(jiao) && jiao.contains("角")) {
                char chi = jiao.charAt(0);
                int num = convertChiUpperNum(chi);
                money += (num * 0.1);
            }

            String fen = matcher.group(7);
            if (StringUtils.isNotBlank(fen) && fen.contains("分")) {
                char chi = fen.charAt(0);
                int num = convertChiUpperNum(chi);
                money += (num * 0.01f);
            }
        }

        return money;
    }

    private static ReceiptInfo getPassport(String result) {
        Matcher matcher = Pattern.compile(".*(FEE.?FOR|FEES)(.*?)(PASS.NO|PASSPORT).{0,2}(A\\d{7}).*").matcher(result);

        ReceiptInfo receiptInfo = new ReceiptInfo();
        if (matcher.matches()) {
            String name = matcher.group(2);
            String passport = matcher.group(4);

            if (StringUtils.isNotBlank(name)) {
                name = name.trim();
                receiptInfo.setName(name);
            }

            if (StringUtils.isNotBlank(passport)) {
                passport = passport.trim();
                receiptInfo.setPassport(passport);
            }
        }

        return receiptInfo;
    }

    private static int convertChiUpperNum(char chi) {
        return switch (chi) {
            case '零' -> 0;
            case '壹' -> 1;
            case '贰' -> 2;
            case '叁' -> 3;
            case '肆' -> 4;
            case '伍' -> 5;
            case '陆' -> 6;
            case '柒' -> 7;
            case '捌' -> 8;
            case '玖' -> 9;
            default -> throw new IllegalArgumentException("ChiUpperNum Error: " + chi);
        };


    }
}
