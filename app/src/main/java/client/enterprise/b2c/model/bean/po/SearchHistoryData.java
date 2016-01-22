package client.enterprise.b2c.model.bean.po;

import java.io.Serializable;

/**
 * 历史搜索实体类
 * Created by raohoulin on 2015.12.28.
 */
public class SearchHistoryData implements Serializable {
    private static final long serialVersionUID = -2666116420091727271L;

    private int id;
    private String search;
    private long writeTime;

    public SearchHistoryData() {
    }

    public SearchHistoryData(String search, long writeTime) {
        this.search = search;
        this.writeTime = writeTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(long writeTime) {
        this.writeTime = writeTime;
    }
}
