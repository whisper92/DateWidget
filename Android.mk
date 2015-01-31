ifeq ($(BIRD_DRAW_NOTE),yes)

LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := $(call all-java-files-under, src)

LOCAL_PACKAGE_NAME := BirdDateViewWidget

include $(BUILD_PACKAGE)

endif
