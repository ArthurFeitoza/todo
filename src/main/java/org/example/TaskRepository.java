package org.example;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;

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
            String sql = "UPDATE tasks SET description = ?, status = ?, completed_at = ?, started_at = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, task.getDescription());
            ps.setString(2, task.getStatus().getValue());
            if (task.getCompletedAt() == null) {
                ps.setNull(3, Types.DATE);
            } else {
                ps.setTimestamp(3,new java.sql.Timestamp(task.getCompletedAt().getTime()));
            }
            if (task.getStartedAt() == null) {
                ps.setNull(4, Types.DATE);
            } else {
                ps.setTimestamp(4,new java.sql.Timestamp(task.getStartedAt().getTime()));
            }
            ps.setLong(5, task.getId());
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
            String sql = "SELECT * FROM tasks WHERE id = ? AND status <> 'I'";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            var resultSet = ps.executeQuery();
            if(resultSet.next()){
                var task = new Tasks(resultSet.getString("description"));
                task.setId(resultSet.getLong("id"));
                task.setStatus(Status.getEnum(resultSet.getString("status")));
                task.setCompletedAt(new Date(resultSet.getTimestamp("completed_at").getTime()));
                task.setCreatedAt(resultSet.getDate("created_at"));
                task.setStartedAt(new Date(resultSet.getTimestamp("started_at").getTime()));
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

    public ArrayList<Tasks> getAll() {
        try {
            var con = ConnectionFactory.createConnection();
            String sql = "SELECT * FROM tasks WHERE status <> 'I'";
            PreparedStatement ps = con.prepareStatement(sql);
            var resultSet = ps.executeQuery();
            ArrayList<Tasks> tasks = new ArrayList<>();

            while (resultSet.next()) {
                    var task = new Tasks(resultSet.getString("description"));
                task.setId(resultSet.getLong("id"));
                task.setStatus(Status.getEnum(resultSet.getString("status")));
                task.setCompletedAt(resultSet.getDate("completed_at"));
                task.setCreatedAt(resultSet.getDate("created_at"));
                task.setStartedAt(resultSet.getDate("started_at"));
                tasks.add(task);
            }
            con.close();
            return tasks;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Can't connect");
        }
        return null;
    }
    public ArrayList<Tasks> getByDescription(String description) {
        try {
            var con = ConnectionFactory.createConnection();
            String sql = "SELECT * FROM tasks WHERE description like ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,"%" + description + "%");

            var resultSet = ps.executeQuery();
            ArrayList<Tasks> tasks = new ArrayList<>();

            while (resultSet.next()) {
                var task = new Tasks(resultSet.getString("description"));
                task.setId(resultSet.getLong("id"));
                task.setStatus(Status.getEnum(resultSet.getString("status")));
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

    public ArrayList<Tasks> getByStatus(Status status) {
        try {
            var con = ConnectionFactory.createConnection();
            String sql = "SELECT * FROM tasks WHERE status = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, status.getValue());
            var resultSet = ps.executeQuery();
            ArrayList<Tasks> tasks = new ArrayList<>();

            while (resultSet.next()) {
                var task = new Tasks(resultSet.getString("description"));
                task.setId(resultSet.getLong("id"));
                task.setStatus(Status.getEnum(resultSet.getString("status")));
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
