package com.bootcamp.todoeasy.data.room


import androidx.room.*
import com.bootcamp.todoeasy.data.models.Category
import com.bootcamp.todoeasy.data.models.Task
import com.bootcamp.todoeasy.data.relantions.CategoryWithTask
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.util.*

@Dao
interface TaskDao {

    /** Delete */
    @Delete
    suspend fun deleteTask(task: Task)


    /** Inserts */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)


    /** Gets */
    @Query("SELECT * FROM task WHERE date(date / 1000, 'unixepoch', 'localtime') = date ('now', 'localtime') AND (status != :hide OR status = 0) AND name LIKE '%' || :search || '%' ORDER BY priority DESC ")
    fun getTasksByDateToday(search: String, hide: Boolean): Flow<List<Task>>

    @Query("SELECT * FROM category")
    fun getCategory(): Flow<List<Category>>

    @Query("SELECT * FROM task WHERE idTask=:taskId")
    suspend fun getTask(taskId: String): Task

    @Query("SELECT * FROM task WHERE created =:created")
    suspend fun getTaskByCreate(created: Date): Task

    @Transaction
    @Query("SELECT * FROM category")
    suspend fun getCategoryWithTask(): List<CategoryWithTask>

    @Query("SELECT * FROM task WHERE date(date / 1000, 'unixepoch', 'localtime') = date ('now', 'localtime') AND categoryName =:categoryName AND (status != :hide OR status = 0) AND name LIKE '%' || :search || '%' ORDER BY priority DESC ")
    fun getTasksByDateTodayCategory(
        search: String,
        hide: Boolean,
        categoryName: String
    ): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE date BETWEEN :startDayWeek AND :endDayWeek AND (status != :hide OR status = 0) AND name LIKE '%' || :search || '%' ORDER BY priority DESC ")
    fun getTaskByDateWeek(
        search: String,
        hide: Boolean,
        startDayWeek: Date,
        endDayWeek: Date
    ): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE date BETWEEN :startDayMonth AND :endDayMonth AND (status != :hide OR status = 0) AND name LIKE '%' || :search || '%' ORDER BY priority DESC ")
    fun getTaskByDateMonth(
        search: String,
        hide: Boolean,
        startDayMonth: Date,
        endDayMonth: Date
    ): Flow<List<Task>>

    /** Updates */
    @Query("UPDATE task SET categoryName =:categoryName WHERE idTask =:taskId")
    suspend fun updateTaskCategory(taskId: String, categoryName: String)

    @Query("UPDATE task SET status =:status WHERE idTask =:taskId")
    suspend fun updateTaskChecked(taskId: String, status: Boolean)

    @Query("UPDATE task SET date =:dueDate WHERE idTask =:taskId")
    suspend fun updateTaskDueDate(taskId: String, dueDate: Date)

    @Query("UPDATE task SET hour =:hour WHERE idTask =:taskId")
    suspend fun updateTaskHour(taskId: String, hour: String)
}