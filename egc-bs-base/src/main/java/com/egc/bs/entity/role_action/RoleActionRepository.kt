package com.egc.bs.entity.role_action

import com.egc.baseapi.jpa.base.BaseRepository
import com.egc.bs.entity.role.RoleEntity
import org.springframework.stereotype.Repository


@Repository
interface RoleActionRepository:BaseRepository<com.egc.bs.entity.role_action.RoleActionEntity> {
    fun findByRoleAndDeleted(role: com.egc.bs.entity.role.RoleEntity, deleted:Boolean):MutableList<com.egc.bs.entity.role_action.RoleActionEntity>
    fun findAllByRoleAndDeleted(role: com.egc.bs.entity.role.RoleEntity?, deleted: Boolean):MutableList<com.egc.bs.entity.role_action.RoleActionEntity>

}