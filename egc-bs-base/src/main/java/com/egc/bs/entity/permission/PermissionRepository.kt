package com.egc.bs.entity.permission

import com.egc.baseapi.jpa.base.BaseRepository
import org.springframework.stereotype.Repository


@Repository
interface PermissionRepository:BaseRepository<com.egc.bs.entity.permission.PermissionEntity> {
}