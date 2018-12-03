package com.itstyle.util;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechSynthesizer;
import com.iflytek.cloud.speech.SpeechUtility;
import com.iflytek.cloud.speech.SynthesizeToUriListener;
/**
 * 讯飞转换PCM
 * 创建者 科帮网 https://blog.52itstyle.com
 * 创建时间	2017年3月6日
 */
public class XunfeiConvertUtil {
	public static Boolean flag = true;
	// 合成监听器
	private final static SynthesizeToUriListener mSynListener = new SynthesizeToUriListener() {
		//progress为合成进度0~100 
		public void onBufferProgress(int progress) {
			
		} 
		//会话合成完成回调接口 URI为合成保存地址，error为错误信息，为null时表示合成会话成功
		public void onSynthesizeCompleted(String uri, SpeechError error) {
			if(error==null){
				System.out.println("生成路径地址:"+uri);
			}else{
				System.out.println(error);
			}
			flag = true;
		}
		@Override
		public void onEvent(int arg0, int arg1, int arg2, int arg3,
				Object arg4, Object arg5) {
			
		}
	};
    public void convert(String message,String path){
    	try {
    		//System.out.println(message);
    		//更换为自己 申请的APPID
			SpeechUtility.createUtility(SpeechConstant.APPID + "=5a97a3b3");
			//Setting.setShowLog(true);

			// 1.创建SpeechSynthesizer对象
			SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer();
			// 2.合成参数设置，详见《MSC Reference Manual》SpeechSynthesizer 类
			mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");// 设置发音人
			mTts.setParameter(SpeechConstant.SPEED, "50");// 设置语速
			mTts.setParameter(SpeechConstant.VOLUME, "80");// 设置音量，范围0~100
			// 3.开始合成
			mTts.setParameter("LIB_NAME_64", "lib_name_64");
			mTts.synthesizeToUri(message,path,mSynListener);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
