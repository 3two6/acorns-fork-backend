package com.egc.bs.entity.role_permission

import com.egc.baseapi.jpa.base.BaseRepository
import com.egc.bs.entity.role.RoleEntity
import org.springframework.stereotype.Repository


@Repository
interface RolePermissionRepository:BaseRepository<com.egc.bs.entity.role_permission.RolePermissionEntity> {

    fun findAllByRoleAndDeleted(role: com.egc.bs.entity.role.RoleEntity, deleted:Boolean):MutableList<com.egc.bs.entity.role_permission.RolePermissionEntity>

}