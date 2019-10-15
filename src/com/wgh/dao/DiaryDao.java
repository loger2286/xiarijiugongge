package com.wgh.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wgh.model.Diary;
import com.wgh.tools.ConnDB;

public class DiaryDao {
	private ConnDB conn = null;// �������ݿ����Ӷ���

	public DiaryDao() {
		conn = new ConnDB();// ʵ�������ݿ����Ӷ���
	}

	/**
	 * ��ѯ�ռ�
	 * 
	 */
	public List<Diary> queryDiary(String sql) {
		ResultSet rs = conn.executeQuery(sql);// ִ�в�ѯ���
		List<Diary> list = new ArrayList<Diary>();
		try {// �����쳣
			while (rs.next()) {
				Diary diary = new Diary();
				diary.setId(rs.getInt(1));// ��ȡ������ID
				diary.setTitle(rs.getString(2));// ��ȡ�������ռǱ���
				diary.setAddress(rs.getString(3));// ��ȡ������ͼƬ��ַ
				Date date;
				try {
					date = DateFormat.getDateTimeInstance().parse(
							rs.getString(4));
					diary.setWriteTime(date);// ����д�ռǵ�ʱ��
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();// ����쳣��Ϣ������̨
				}

				diary.setUserid(rs.getInt(5));// ��ȡ�������û�ID
				diary.setUsername(rs.getString(6));// ��ȡ�������û���
				list.add(diary);// ���ռ���Ϣ���浽list������

			}
		} catch (SQLException e) {
			e.printStackTrace();// ����쳣��Ϣ
		} finally {
			conn.close();// �ر����ݿ�����
		}
		return list;
	}

	/**
	 * ���ܣ�����Ź����ռǵ����ݿ�
	 * 
	 */
	public int saveDiary(Diary diary) {
		String sql = "INSERT INTO tb_diary (title,address,userid) VALUES('"
				+ diary.getTitle() + "','" + diary.getAddress() + "',"
				+ diary.getUserid() + ")";		//�������ݵ�SQL���
		int ret = conn.executeUpdate(sql);// ִ�и������
		conn.close();// �ر����ݿ�����
		return ret;
	}

	/**
	 * ɾ��ָ���ռ�
	 * 
	 */
	public int delDiary(int id) {
		String sql = "DELETE FROM tb_diary WHERE id=" + id;
		int ret = 0;
		try {
			ret = conn.executeUpdate(sql);// ִ�и������
		} catch (Exception e) {
			e.printStackTrace();// ����쳣��Ϣ
		} finally {
			conn.close();// �ر���������
		}
		return ret;
	}
}
