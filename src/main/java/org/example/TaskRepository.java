package org.example;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class TaskRepository {

    public void create(Tasks task) {
        try {
            var con = ConnectionFactory.createConnection();
            String sql = "INSERT INTO tasks (description) VALUES (?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, task.getDescription());
            ps.execute();
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Can't connect");
            e.printStackTrace();
        }

    }

    public void update(Tasks task) {
        try {
            var con = ConnectionFactory.createConnection();
            String sql = "UPDATE tasks SET description = ?, is_completed = ?, completed_at = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, task.getDescription());
            ps.setBoolean(2, task.isCompleted());
            if (task.getCompletedAt() == null) {
                ps.setNull(3, Types.DATE);
            } else {
                ps.setTimestamp(3,new java.sql.Timestamp(task.getCompletedAt().getTime()));
            }
            ps.setLong(4, task.getId());
            ps.execute();
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Can't connect");
            e.printStackTrace();
        }
    }

    public Tasks getById(long id) {
        try {
            var con = ConnectionFactory.createConnection();
            String sql = "SELECT * FROM tasks WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            var resultSet = ps.executeQuery();
            if(resultSet.next()){
                var task = new Tasks(resultSet.getString("description"));
                task.setId(resultSet.getLong("id"));
                task.setCompleted(resultSet.getBoolean("is_completed"));
                task.setCompletedAt(resultSet.getDate("completed_at"));
                task.setCreatedAt(resultSet.getDate("created_at"));
                con.close();
                return task;
            }
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Can't connect");
            e.printStackTrace();
        }
        return null;
    }
    public void delete(Long id) {
        try {
            var con = ConnectionFactory.createConnection();
            String sql = "DELETE FROM tasks WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.execute();
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Can't connect");
            e.printStackTrace();
        }
    }
    public ArrayList<Tasks> getAll() {
        try {
            var con = ConnectionFactory.createConnection();
            String sql = "SELECT * FROM tasks";
            PreparedStatement ps = con.prepareStatement(sql);
            var resultSet = ps.executeQuery();
            ArrayList<Tasks> tasks = new ArrayList<>();
            while (resultSet.next()) {
                var task = new Tasks(resultSet.getString("description"));
                task.setId(resultSet.getLong("id"));
                task.setCompleted(resultSet.getBoolean("is_completed"));
                task.setCompletedAt(resultSet.getDate("completed_at"));
                task.setCreatedAt(resultSet.getDate("created_at"));
                tasks.add(task);
            }
            con.close();
            return tasks;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Can't connect");
            e.printStackTrace();
        }
        return null;
    }
}
