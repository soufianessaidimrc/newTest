package dao;

import entity.Task;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoListManager {
    private final DataSource dataSource;
    private Connection connect = null;

    public TodoListManager(DataSource dataSource) {
        this.dataSource = dataSource;
        try {
            this.connect = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Task> getAll() {
        List<Task> tasks = new ArrayList<>();
        ResultSet res = null;
        String sql = "SELECT * FROM tasks";
        try (Statement st = connect.createStatement()) {
            res = st.executeQuery(sql);
            while (res.next()) {
                int id = res.getInt("task_id");
                String comment = res.getString("comment");
                LocalDate createdAt = res.getDate("editedAt").toLocalDate();
                Boolean status = res.getBoolean("status");
                Task t = new Task(id, comment, createdAt, status);
                tasks.add(t);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return tasks;
    }

    public Task getTaskById(int id) {
        ResultSet res = null;
        String sql = "SELECT * FROM tasks WHERE task_id =?";
        Task t = new Task();
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setInt(1, id);
            res = st.executeQuery();

            while (res.next()) {
                String comment = res.getString("comment");
                LocalDate createdAt = res.getDate("editedAt").toLocalDate();
                Boolean status = res.getBoolean("status");
                t.setId(id);
                t.setComment(comment);
                t.setEditedAt(createdAt);
                t.setStatus(status);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return t;
    }

    public void addTask(Task task) {
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            String sql = "INSERT INTO tasks (comment, editedAt) VALUES (?, ?)";
            myStmt = connect.prepareStatement(sql);

            myStmt.setString(1, task.getComment());
            myStmt.setDate(2, Date.valueOf(task.getEditedAt()));
            myStmt.execute();

            System.out.println(" add task " + task.getComment());

        } catch (Exception e) {
            System.out.println(e.getMessage() + " save");
        }

    }

    public void updateTask(Task task) {
        PreparedStatement myStmt = null;

        try {
            String sql = "UPDATE `tasks` SET `comment`=?,`editedAt`=?,`status`=? WHERE task_id=?";
            myStmt = connect.prepareStatement(sql);
            myStmt.setString(1, task.getComment());
            myStmt.setDate(2, Date.valueOf(task.getEditedAt()));
            myStmt.setBoolean(3, task.getStatus());
            myStmt.setInt(4, task.getId());
            myStmt.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage() + " update");
        }
    }


    public void deleteTask(int id) {
        try {
            Statement myStmt = connect.createStatement();
            String sql = "delete  from tasks where task_id=\"" + id + "\"";
            myStmt.executeUpdate(sql);
            System.out.println("Remove data for " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeall() {
        try {
            Statement myStmt = connect.createStatement();
            String sql = "delete  from tasks";
            myStmt.executeUpdate(sql);
            System.out.println("Delete all");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
