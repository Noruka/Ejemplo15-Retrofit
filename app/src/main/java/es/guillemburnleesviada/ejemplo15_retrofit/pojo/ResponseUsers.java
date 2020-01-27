package es.guillemburnleesviada.ejemplo15_retrofit.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseUsers {
    private long page;
    @SerializedName("per_page")
    private long perPage;
    private long total;
    @SerializedName("total_pages")
    private long totalPages;
    private List<User> data;

    public long getPage() { return page; }
    public void setPage(long value) { this.page = value; }

    public long getPerPage() { return perPage; }
    public void setPerPage(long value) { this.perPage = value; }

    public long getTotal() { return total; }
    public void setTotal(long value) { this.total = value; }

    public long getTotalPages() { return totalPages; }
    public void setTotalPages(long value) { this.totalPages = value; }

    public List<User> getData() { return data; }
    public void setData(List<User> value) { this.data = value; }
}
