package com.pingdu.dao.itemDao;






import static com.pingdu.manager.ThreadLocalManager.em;



import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.pingdu.entity.item.Item;
import com.pingdu.utility.PublicVariable;

@Repository
public class ItemDao {

	public Item findById(Integer id) {
		return em().find(Item.class, id);
	}
	/**
	 *查看条目列表
	 */
	public List<Item> getItems(int page)
	{
		String jpql = "select i from Item i left join fetch i.points where 1=1  ";
		TypedQuery<Item> query = em().createQuery(jpql, Item.class);
		int head = (page - 1) * PublicVariable.rows;
		query.setFirstResult(head);
		query.setMaxResults(PublicVariable.rows);
		List<Item> list = query.getResultList();
		return list;
	}
	/**
	 * 获得页面总数
	 * @return
	 */
	public int sum() {
		String jpql = "select i from Item i where 1=1";
		TypedQuery<Item> query = em().createQuery(jpql, Item.class);
		int SUM = ((query.getResultList().size()) - 1) / PublicVariable.rows + 1;
		return SUM;
	}
	/**
	 * 查询条目
	 * @param content
	 * @param page
	 * @return
	 */
	public List<Item> searchItems(String content,int page)
	{
		String jpql =  "select i from Item i left join fetch i.points where i.itemInfo like :content order by i.itemCode";
		TypedQuery<Item> query = em().createQuery(jpql, Item.class);
		query.setParameter("content", "%"+content+"%");
		int head = (page - 1) * PublicVariable.rows;
		query.setFirstResult(head);
		query.setMaxResults(PublicVariable.rows);
		List<Item> list = query.getResultList();
		return list;
	}
	public Integer sumOfSearch(String content) {
		String jpql =  "select i from Item i where i.itemInfo like :content order by i.itemCode";
		TypedQuery<Item> query = em().createQuery(jpql, Item.class);
		query.setParameter("content", "%"+content+"%");
		int SUM = ((query.getResultList().size()) - 1) / PublicVariable.rows + 1;
		return SUM;

	}
	/**
	 * 删除条目
	 */
	public  void delete(Integer itemcode) {
		Item i = findById(itemcode);
		System.out.println(i.getItemInfo());
		if (i != null );
			em().remove(i);
	}
	/**
	 * 获得条目
	 * @param itemCode
	 * @return
	 */
	public  Item getItem(Integer itemCode) {
		
			String jpql = new String();
			jpql = "select i from Item i where i.itemCode=:itemCode";
			TypedQuery<Item> query = em().createQuery(jpql, Item.class);
			query.setParameter("itemCode", itemCode);
			Item i = query.getSingleResult();
			return i;
		
		
	}
	
	public  void update(Item item) {
		em().merge(item);
	}
	
	public void insert(Item item) {
		em().persist(item);
	}
}
