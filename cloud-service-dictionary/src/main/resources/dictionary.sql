-- db.dictionary definition  字典表

CREATE TABLE `dictionary` (
                              `id` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '主键',
                              `type` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '字典类型',
                              `type_key` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '字典键值',
                              `type_value` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '字典值',
                              `bz` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;