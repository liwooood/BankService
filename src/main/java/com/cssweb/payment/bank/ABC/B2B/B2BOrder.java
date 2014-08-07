package com.cssweb.payment.bank.ABC.B2B;

/**
 * Created by chenhf on 2014/8/7.
 */
public class B2BOrder {

    public String abcB2BPayment() {

        //2、生成TrnxInfo对象
        TrnxItems tTrnxItems = new TrnxItems();
        tTrnxItems.addTrnxItem(new TrnxItem("0001",     "显示器",       1000.00f, 2));
        tTrnxItems.addTrnxItem(new TrnxItem("0002",     "硬盘",         600.00f,  5));
        tTrnxItems.addTrnxItem(new TrnxItem("IP000001", "中国移动IP卡", 100.00f,  1));

        TrnxRemarks tTrnxRemarks = new TrnxRemarks();
        tTrnxRemarks.addTrnxRemark(new TrnxRemark("合同号",  "555000000"));
        tTrnxRemarks.addTrnxRemark(new TrnxRemark("采购时间","2003/11/12 14:23:34"));
        tTrnxRemarks.addTrnxRemark(new TrnxRemark("交易类型","买入"));
        tTrnxRemarks.addTrnxRemark(new TrnxRemark("其它说明","能够使买方确信该交易是自己交易的信息"));

        TrnxInfo tTrnxInfo = new TrnxInfo();
        tTrnxInfo.setTrnxOpr("TrnxOperator0001");
        tTrnxInfo.setTrnxRemarks(tTrnxRemarks);
        tTrnxInfo.setTrnxItems(tTrnxItems);

        //3、生成直接支付请求对象
        FundTransferRequest tFundTransferRequest = new FundTransferRequest();
        tFundTransferRequest.setTrnxInfo(tTrnxInfo);                           //设定交易细项        （必要信息）
        tFundTransferRequest.setMerchantTrnxNo("");               //设定商户交易编号    （必要信息）
        tFundTransferRequest.setTrnxAmount(100.00);                       //设定交易金额        （必要信息）
        tFundTransferRequest.setTrnxDate("");                           //设定交易日期        （必要信息）
        tFundTransferRequest.setTrnxTime("");                           //设定交易时间        （必要信息）
        tFundTransferRequest.setAccountDBNo("");                     //设定收款方账号      （必要信息）
        tFundTransferRequest.setAccountDBName("");                 //设定收款方账户名    （必要信息）
        tFundTransferRequest.setAccountDBBank("");                 //设定收款方账户开户行联行号（必要信息）
        tFundTransferRequest.setResultNotifyURL("");             //设定交易结果回传网址（必要信息）
        tFundTransferRequest.setMerchantRemarks("");             //设定商户备注信息

        // tFundTransferRequest.genSignature();

        return null;
    }
}
