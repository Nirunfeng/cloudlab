package com.titan.arm.pinyin;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:  拼音工具
 * @Date 2024/6/24 20:15
 **/
public class PinyinUtil {

    private static String duoyinzi="长,重";

    /**
     * 获取中文全拼
     *
     * @param name 需要转换的中文
     * @return 全拼结果
     **/
    public static String getFullPinyin(String name) {
        // 创建格式化对象
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        //设置大小写格式
        outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        //设置声调格式
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        // 放置输入结果
        StringBuilder result = new StringBuilder();
        // 字符数组
        char[] charArray = name.toCharArray();
        // 遍历字符
        for (char c : charArray) {
            // 中文会被变成全拼，非中文会被直接拼接在结果字符串中
            if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                String[] pinyinArray = new String[0];
                try {
                    pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, outputFormat);
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
                if (pinyinArray != null) {
                    result.append(pinyinArray[0]);
                }
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * 获取中文首字母
     *
     * @param name 需要转换的中文
     * @return 首字母结果
     **/
    public static String getPinyinInitial(String name) {
        // 创建格式化对象
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        //设置大小写格式
        outputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        //设置声调格式
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        // 放置输入结果
        StringBuilder result = new StringBuilder();
        // 字符数组
        char[] charArray = name.toCharArray();
        // 遍历字符
        for (char c : charArray) {
            if (duoyinzi.contains(String.valueOf(c))){
                result.append("C");
                continue;
            }
            // 中文会被变成拼音首字母，非中文会被直接拼接在结果字符串中
            if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                String[] pinyinArray = new String[0];
                try {
                    pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, outputFormat);
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
                if (pinyinArray != null) {
                    result.append(pinyinArray[0].charAt(0));
                }
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * 获取第一个字的首字母
     * @param name
     * @return
     */
    public static String getFirstPinyinInitial(String name){
        // 创建格式化对象
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        //设置大小写格式
        outputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        //设置声调格式
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        // 放置输入结果
        StringBuilder result = new StringBuilder();
        // 字符数组，获取第一个
        char firstChar = name.toCharArray()[0];
        if (duoyinzi.contains(String.valueOf(firstChar))){
            return "C";
        }
        // 中文会被变成拼音首字母，非中文会被直接拼接在结果字符串中
        if (Character.toString(firstChar).matches("[\\u4E00-\\u9FA5]+")) {
            String[] pinyinArray = new String[0];
            try {
                pinyinArray = PinyinHelper.toHanyuPinyinStringArray(firstChar, outputFormat);
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                badHanyuPinyinOutputFormatCombination.printStackTrace();
            }
            if (pinyinArray != null) {
                result.append(pinyinArray[0].charAt(0));
            }
        } else {
            result.append(firstChar);
        }
        return result.toString();
    }

}
