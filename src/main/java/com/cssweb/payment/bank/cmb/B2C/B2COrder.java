package com.cssweb.payment.bank.CMB.B2C;

/**
 * Created by chenhf on 2014/7/30.
 */
public class B2COrder {



    public void B2CSign()
    {
        // 商户密钥
        String key = "21asfDSDD344asdA";
        // 订单日期
        String date = "20140731";
        // 开户分行号
        String branchId = "0010";
        // 商户号
        String cono = "000818";
        // 订单号
        String billNo = "0000000003";
        // 订单金额
        String amount = "100.00";
        // 商户自定义参数
        String merchantPara = "k=v";
        // 商户接收通知
        String merchantUrl = "http://cashier.cecpay.com/notify";
        // 付款方用户标识
        String payerId = "user1";
        // 收款方用户标识
        String payeeId = "user2";
        // 商户取得的客户端ip
        String clientIp = "192.168.1.1";
        // 商品类型
        String goodsType = "54011600"; // 网上支付
        // 保留
        String reserved = "";

        cmb.MerchantCode mc = new cmb.MerchantCode();

        String signData = mc.genMerchantCode(key, date, branchId, cono, billNo, amount, merchantPara, merchantUrl, payerId, payeeId, clientIp, goodsType, reserved);
        System.out.println("产生的校验码为：" + signData);
    }

    public void B2CVerify()
    {
        String notifyResult = "http://cashier.cecpay.com/notify?Succeed=Y&CoNo=000818&BillNo=000000&Amount=100.00&Date=20140731&MerchantPara=k=v&BillExInfo=00622609******61810000000000000000000000000000000000000000000000&Msg=00100008182014073100000000000000000000&Signature=124|74|130|221|6|217|254|246|179|6|251|21|2|55|190|60|189|222|233|97|1|12|129|144|16|127|66|238|134|225|142|235|91|213|172|166|56|7|9|60|198|174|146|234|79|207|155|202|125|62|135|145|116|171|252|212|79|10|122|108|169|252|71|182|";
        try {
            cmb.netpayment.Security cmb = new cmb.netpayment.Security("CMB.public.key");
            cmb.checkInfoFromBank(notifyResult.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void B2B()
    {

    }

    public static void main(String[] args)
    {
        B2COrder service = new B2COrder();
        service.B2CSign();
    }
}
