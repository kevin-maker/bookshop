package edu.jlxy.lzh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> {
    public static final Integer PAGE_SIZE = 4;
    //当前页码
    private Integer pageNo;
    //总页码
    private Integer totalPage;
    //当前页显示数量
    private Integer pageSize = PAGE_SIZE;
    //总记录数
    private Integer totalCount;
    //当前页数据
    private List<T> data;
    //分页条的请求地址
    private String url;

    public void setPageNo(Integer pageNo) {
        //检查数据边界
        if(pageNo < 1){
            pageNo = 1;
        }else if (pageNo > totalPage){
            pageNo = totalPage;
        }
        this.pageNo = pageNo;
    }
}
