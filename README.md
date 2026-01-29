# ✅ To-Do List App

<div align="center">
  
  ![Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
  ![Java](https://img.shields.io/badge/Language-Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
  ![SQLite](https://img.shields.io/badge/Database-SQLite-003B57?style=for-the-badge&logo=sqlite&logoColor=white)
  
</div>

## 📋 Overview

**To-Do List App** is a simple yet powerful task management application for Android. Built with Java, it helps users organize their daily tasks, set priorities, and track their productivity with an intuitive and clean interface.

## ✨ Key Features

- **➕ Add Tasks**: Quickly create new tasks with title and description
- **✓ Mark Complete**: Check off completed tasks
- **✏️ Edit Tasks**: Update task details anytime
- **🗑️ Delete Tasks**: Remove tasks you no longer need
- **💾 Persistent Storage**: Tasks saved locally using SQLite
- **📱 Clean UI**: Simple and intuitive user interface
- **🎨 Material Design**: Modern Android design guidelines
- **⚡ Fast Performance**: Smooth and responsive experience

## 🛠️ Tech Stack

- **Language**: Java
- **Database**: SQLite (Room or native SQLite)
- **UI**: XML Layouts with Material Design
- **Architecture**: MVC/MVVM
- **RecyclerView**: Efficient task list display
- **Min SDK**: Android 5.0 (API 21)

## 📱 App Features

### Task Management
- **Create**: Add new tasks with details
- **Read**: View all tasks in a list
- **Update**: Edit existing tasks
- **Delete**: Remove completed or unwanted tasks

### Task Properties
- Task title
- Task description
- Creation date/time
- Completion status
- Priority level (optional)
- Due date (optional)

### User Interface
- Clean task list view
- Add task button (FAB)
- Swipe to delete
- Long press to edit
- Checkbox for completion
- Empty state message

## 🎨 Design

### Main Screen Layout
```
┌─────────────────────────┐
│   To-Do List  [+]       │
├─────────────────────────┤
│ ☐ Task 1                │
│   Description...        │
├─────────────────────────┤
│ ☑ Task 2 (Completed)    │
│   Description...        │
├─────────────────────────┤
│ ☐ Task 3                │
│   Description...        │
└─────────────────────────┘
```

## 🚀 Getting Started

### Prerequisites

- Android Studio Arctic Fox or later
- Android SDK 21 or higher
- Java Development Kit (JDK) 8+

### Installation

1. **Clone the repository**:
```bash
git clone https://github.com/AhmedSh10/To-DoListApp.git
```

2. **Open in Android Studio**

3. **Sync Gradle dependencies**

4. **Build and run the project**

## 📂 Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/todolist/
│   │   │       ├── MainActivity.java
│   │   │       ├── TaskAdapter.java
│   │   │       ├── Task.java
│   │   │       ├── DatabaseHelper.java
│   │   │       └── utils/
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml
│   │   │   │   ├── item_task.xml
│   │   │   │   └── dialog_add_task.xml
│   │   │   ├── values/
│   │   │   └── drawable/
│   │   └── AndroidManifest.xml
```

## 🔧 Implementation

### Task Model
```java
public class Task {
    private int id;
    private String title;
    private String description;
    private boolean isCompleted;
    private long createdAt;
    
    // Constructors, getters, and setters
}
```

### Database Helper
```java
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TodoDB";
    private static final String TABLE_TASKS = "tasks";
    
    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", task.getTitle());
        values.put("description", task.getDescription());
        values.put("is_completed", task.isCompleted());
        db.insert(TABLE_TASKS, null, values);
        db.close();
    }
    
    public List<Task> getAllTasks() {
        // Implementation
    }
    
    public void updateTask(Task task) {
        // Implementation
    }
    
    public void deleteTask(int id) {
        // Implementation
    }
}
```

### RecyclerView Adapter
```java
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> tasks;
    private OnTaskClickListener listener;
    
    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.titleTextView.setText(task.getTitle());
        holder.checkBox.setChecked(task.isCompleted());
        
        holder.checkBox.setOnClickListener(v -> {
            task.setCompleted(holder.checkBox.isChecked());
            listener.onTaskStatusChanged(task);
        });
    }
}
```

## 🎯 Use Cases

- **Daily Planning**: Organize daily tasks and activities
- **Work Management**: Track work-related tasks
- **Shopping Lists**: Create and manage shopping lists
- **Study Planning**: Organize study tasks and assignments
- **Personal Goals**: Track personal objectives
- **Project Management**: Manage small project tasks

## 🔮 Future Enhancements

- [ ] Add task categories/tags
- [ ] Set task priorities (High, Medium, Low)
- [ ] Add due dates and reminders
- [ ] Implement notifications
- [ ] Add task search functionality
- [ ] Sort and filter options
- [ ] Dark mode support
- [ ] Cloud sync (Firebase)
- [ ] Recurring tasks
- [ ] Task statistics and analytics
- [ ] Export tasks to file
- [ ] Share tasks with others

## 📊 Database Schema

```sql
CREATE TABLE tasks (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    description TEXT,
    is_completed INTEGER DEFAULT 0,
    created_at INTEGER,
    due_date INTEGER,
    priority INTEGER DEFAULT 0
);
```

## 🤝 Contributing

Contributions are welcome! Feel free to:
- Add new features
- Improve UI/UX
- Fix bugs
- Optimize database queries
- Enhance documentation

## 📄 License

This project is available for educational and personal use.

## 👨‍💻 Developer

**Ahmed Sherif**

- GitHub: [@AhmedSh10](https://github.com/AhmedSh10)
- LinkedIn: [Ahmed Sherif](https://linkedin.com/in/dev-ahmed-sherif)

## 🙏 Acknowledgments

- Android documentation
- SQLite database documentation
- Material Design guidelines
- Open-source community

## 📚 Learning Resources

This project is perfect for learning:
- Android CRUD operations
- SQLite database management
- RecyclerView implementation
- Material Design components
- Java Android development

---

<div align="center">
  
  **⭐ If you find this project useful, please consider giving it a star!**
  
  **✅ Get things done, one task at a time!**
  
</div>
