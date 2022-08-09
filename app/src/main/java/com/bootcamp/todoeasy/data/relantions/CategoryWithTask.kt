package com.bootcamp.todoeasy.data.relantions

import androidx.room.Embedded
import androidx.room.Relation
import com.bootcamp.todoeasy.data.models.Category
import com.bootcamp.todoeasy.data.models.Task

data class CategoryWithTask(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "idCategory",
        entityColumn = "categoryName"
    )
    val taskList: List<Task>
)