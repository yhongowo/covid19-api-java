package com.example.ncovapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Data
@NoArgsConstructor
public class AreaStat {
    private String provinceName;
    private String provinceShortName;
    private int confirmedCount;
    private int suspectedCount;
    private int curedCount;
    private int deadCount;
    private String comment;
    private List<CitiesBean> cities;
    private long modifyTime;
    public static class CitiesBean {
        /**
         * cityName : 武汉
         * confirmedCount : 495
         * suspectedCount : 0
         * curedCount : 31
         * deadCount : 23
         */

        private String cityName;
        private int confirmedCount;
        private int suspectedCount;
        private int curedCount;
        private int deadCount;
        private String provinceName;
        private long modifyTime;

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public int getConfirmedCount() {
            return confirmedCount;
        }

        public void setConfirmedCount(int confirmedCount) {
            this.confirmedCount = confirmedCount;
        }

        public int getSuspectedCount() {
            return suspectedCount;
        }

        public void setSuspectedCount(int suspectedCount) {
            this.suspectedCount = suspectedCount;
        }

        public int getCuredCount() {
            return curedCount;
        }

        public void setCuredCount(int curedCount) {
            this.curedCount = curedCount;
        }

        public int getDeadCount() {
            return deadCount;
        }

        public void setDeadCount(int deadCount) {
            this.deadCount = deadCount;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        @Override
        public String toString() {
            return "CitiesBean{" +
                    "cityName='" + cityName + '\'' +
                    ", confirmedCount=" + confirmedCount +
                    ", suspectedCount=" + suspectedCount +
                    ", curedCount=" + curedCount +
                    ", deadCount=" + deadCount +
                    ", provinceName='" + provinceName + '\'' +
                    ", modifyTime=" + modifyTime +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CitiesBean that = (CitiesBean) o;
            return confirmedCount == that.confirmedCount &&
                    suspectedCount == that.suspectedCount &&
                    curedCount == that.curedCount &&
                    deadCount == that.deadCount;
        }

        @Override
        public int hashCode() {
            return Objects.hash(cityName, confirmedCount, suspectedCount, curedCount, deadCount, provinceName, modifyTime);
        }
    }
}
