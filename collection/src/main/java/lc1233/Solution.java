package lc1233;

import java.lang.annotation.Target;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author payno
 * @date 2019/11/24 13:31
 * @description
 *      你是一位系统管理员，手里有一份文件夹列表 folder，你的任务是要删除该列表中的所有 子文件夹，并以 任意顺序 返回剩下的文件夹。
 *
 * 我们这样定义「子文件夹」：
 *
 *     如果文件夹 folder[i] 位于另一个文件夹 folder[j] 下，那么 folder[i] 就是 folder[j] 的子文件夹。
 *
 * 文件夹的「路径」是由一个或多个按以下格式串联形成的字符串：
 *
 *     / 后跟一个或者多个小写英文字母。
 *
 *     首先保证输入的数组是有序的
 */
public class Solution {
    public List<String> removeSubfolders(String[] folder) {
        /**
         * HashSet 根据hash与equals比较  TreeSet 根据compareTo排序  LinkedHashSet 输入输出有序
         */
        final Set<String> dirs= new HashSet<String>();
        for(String dir:folder){
            dirs.add(dir);
        }
        return dirs.stream().filter(str->{
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)=='/'){
                    if (dirs.contains(str.substring(0,i))){
                        return false;
                    }
                }
            }
            return true;
        }).collect(Collectors.toList());
    }

    public List<String> removeSubfolders2(String[] folder) {
        /**
         * HashSet 根据hash与equals比较  TreeSet 根据compareTo排序  LinkedHashSet 输入输出有序
         */
        final Set<String> dirs= new HashSet<String>();
        for(String dir:folder){
            dirs.add(dir);
        }
        Iterator<String> iterator=dirs.iterator();
        String str;
        boolean flag;
        while(iterator.hasNext()){
            str=iterator.next();
            flag=false;
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)=='/'){
                    if (dirs.contains(str.substring(0,i))){
                        flag=true;
                    }
                }
            }
            if (flag){
                iterator.remove();
            }
        }
        return dirs.stream().collect(Collectors.toList());
    }

    public List<String> removeSubfolders3(String[] folder) {
        /**
         * HashSet 根据hash与equals比较  TreeSet 根据compareTo排序  LinkedHashSet 输入输出有序
         */
        final Set<String> dirs= new TreeSet<String>();
        for(String dir:folder){
            dirs.add(dir);
        }
        return dirs.stream().filter(str->{
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)=='/'){
                    if (dirs.contains(str.substring(0,i))){
                        return false;
                    }
                }
            }
            return true;
        }).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Solution solution=new Solution();
        solution.removeSubfolders2(new String[]{"/a","/a/b","/c/d","/c/d/e","/c/f"}).forEach(System.out::println);
    }
}
