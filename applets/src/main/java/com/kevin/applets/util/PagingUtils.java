package com.kevin.applets.util;


import com.kevin.applets.entity.DataGrid;
import com.kevin.applets.entity.PagingDto;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

public class PagingUtils {

    /**
     * @param entityManager
     * @param grid          自己定義的返回grid
     * @param hql
     * @param paramMap
     * @param pagingDto
     * @param tableAlias
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> DataGrid<T> exeHql(EntityManager entityManager, DataGrid<T> grid, String hql, Map<String, Object> paramMap, PagingDto pagingDto, String tableAlias) {

        // 创建Query
        String orderSql = pagingDto == null || pagingDto.getOrder() == null ? "" : pagingDto.toOrderSql(tableAlias);
        Query query = entityManager.createQuery("SELECT " + tableAlias + " " + hql.toString() + orderSql);
        Query queryCount = entityManager.createQuery("SELECT COUNT(1) " + hql.toString());

        // 填充参数
        for (Map.Entry<String, Object> qParam : paramMap.entrySet()) {
            query.setParameter(qParam.getKey(), qParam.getValue());
            queryCount.setParameter(qParam.getKey(), qParam.getValue());
        }

        // 分页
        if (pagingDto != null && pagingDto.getPageNo() != null) {
            query.setFirstResult((pagingDto.getPageNo() - 1) * pagingDto.getPageSize());
            query.setMaxResults(pagingDto.getPageSize());
        }

        // 填充检索结果
        grid.setRows((List<T>) query.getResultList());

        grid.setTotal((Long) queryCount.getSingleResult());
        return grid;
    }

    /**
     * 不帶 grid參數
     *
     * @param entityManager
     * @param hql
     * @param paramMap
     * @param pagingDto
     * @param tableAlias
     * @return
     */
    public static <T> DataGrid<T> exeHql(EntityManager entityManager, String hql, Map<String, Object> paramMap, PagingDto pagingDto, String tableAlias) {
        DataGrid<T> grid = new DataGrid<T>(true, 0);

        return exeHql(entityManager, grid, hql, paramMap, pagingDto, tableAlias);
    }

}
