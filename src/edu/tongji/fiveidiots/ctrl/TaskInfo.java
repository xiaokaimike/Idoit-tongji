/*
 * Author: Qrc
 * Date:2012-05-28
 */
package edu.tongji.fiveidiots.ctrl;

import java.util.ArrayList;
import java.util.Date;

/*
 * TaskInfo记录每个任务详细信息
 * 成员：见下面注释
 * 方法：该类中方法不对外部开放，仅供TaskController类调用
 * void copy(TaskInfo aTask) 复制aTask的任务信息
 * void ImportTag(ArrayList<String> tag) 将tag数组中内容复制给任务中的标签
 * ArrayList<String> ExportTag() 导出任务中标签的内容
 * void addTag(String atag) 新增一个tag
 * void deleteTag(int id) 删除一个tag
 * boolean searchTag(String str) 搜索一个tag如果存在，返回true
 * boolean IsExpire() 返回该任务是否过期
 * void SetExpire() 设置该任务过期
 * boolean IsFinish() 返回该任务是否完成
 * void SetFinish() 设置该任务状态为完成
 * boolean IsDetermine()
 * void SetDetermine()
 * void FinishCycle(int interrupt,double percent,Date cur) 该任务完成一个蕃茄钟，调用一次该函数
 * 
 */
public class TaskInfo {
	// 优先级，前驱任务ID，后继ID，已经完成的番茄时钟数，尚待完成的番茄时钟数，完成任务的方式，中断个数，任务ID
	private int pri, pre_id, next_id, pcycle, ncycle, way, interrupt, id; 
	private double percent; // 完成任务的百分比
	private String name, addr, hint; // 任务名称，地址，注释
	private Date starttime, deadline; // 任务开始时间，截止时间
	private ArrayList<String> tag; // 任务标签们
	private boolean expire, finish, determine; // 任务是否过期，是否完成
	
	public TaskInfo(){
		
	}

	public TaskInfo(int pri, int pre_id, int next_id, int pcycle, int ncycle,
			int way, int interrupt, int id, double percent, String name,
			String addr, String hint, Date starttime, Date deadline,
			ArrayList<String> tag, boolean expire, boolean finish,
			boolean determine) {
		this.id = id;
		this.name = name;
		this.addr = addr;
		this.hint = hint;
		this.pri = pri;
		this.pre_id = pre_id;
		this.next_id = next_id;
		this.pcycle = pcycle;
		this.ncycle = ncycle;
		this.way = way;
		this.deadline = deadline;
		this.starttime = starttime;
		this.expire = expire;
		this.finish = finish;
		this.percent = percent;
		this.tag = new ArrayList<String>(tag);
		this.determine = determine;
	}

	public void copy(TaskInfo aTask) {
		this.id = aTask.getId();
		this.name = aTask.getName();
		this.addr = aTask.getAddr();
		this.hint = aTask.getHint();
		this.pri = aTask.getPri();
		this.pre_id = aTask.getPre_id();
		this.next_id = aTask.getNext_id();
		this.pcycle = aTask.getPcycle();
		this.ncycle = aTask.getNcycle();
		this.way = aTask.getWay();
		this.deadline = aTask.getDeadline();
		this.percent = aTask.getPercent();
		this.expire = aTask.IsExpire();
		this.finish = aTask.IsFinish();
		this.determine = aTask.IsDetermine();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPcycle() {
		return pcycle;
	}

	public void setPcycle(int pcycle) {
		this.pcycle = pcycle;
	}

	public int getNcycle() {
		return ncycle;
	}

	public void setNcycle(int ncycle) {
		this.ncycle = ncycle;
	}

	public int getPri() {
		return pri;
	}

	public void setPri(int pri) {
		this.pri = pri;
	}

	public int getPre_id() {
		return pre_id;
	}

	public void setPre_id(int pre_id) {
		this.pre_id = pre_id;
	}

	public int getNext_id() {
		return next_id;
	}

	public void setNext_id(int next_id) {
		this.next_id = next_id;
	}

	public int getWay() {
		return way;
	}

	public void setWay(int way) {
		this.way = way;
	}

	public int getInterrupt() {
		return interrupt;
	}

	public void setInterrupt(int interrupt) {
		this.interrupt = interrupt;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public void importTags(ArrayList<String> tag) {
		this.tag.clear();
		this.tag.addAll(tag);
	}

	public ArrayList<String> exportTag() {
		return this.tag;
	}

	public void addTag(String atag) {
		tag.add(atag);
	}

	public void deleteTag(int id) {
		tag.remove(id);
	}

	public boolean searchTag(String str) {
		for (int i = 0; i < tag.size(); ++i) {
			if (str.equals(tag.get(i))) {
				return true;
			}
		}
		return false;
	}

	public boolean IsExpire() {
		return expire;
	}

	public void SetExpire() {
		expire = true;
	}

	public boolean IsFinish() {
		return finish;
	}

	public void SetFinish() {
		finish = true;
	}

	public boolean IsDetermine() {
		return determine;
	}

	public void SetDetermine() {
		determine = true;
	}

	public void FinishCycle(int interrupt, double percent, Date cur) {
		this.interrupt += interrupt;
		this.percent += percent;
		if (this.percent >= 100)
			finish = true;
		if (this.deadline.before(cur))
			expire = true;
		pcycle++;
		ncycle = (int) Math.ceil((100.0 - percent) / (percent / pcycle));
	}

}