package com.aICE.API.dto;

public class DashboardStatsDTO {

    private long totalUsers;

    private long totalPosts;

    private long totalComments;

    private double topViralityScore;

    public DashboardStatsDTO(
            long totalUsers,
            long totalPosts,
            long totalComments,
            double topViralityScore
    ) {
        this.totalUsers = totalUsers;
        this.totalPosts = totalPosts;
        this.totalComments = totalComments;
        this.topViralityScore = topViralityScore;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public long getTotalPosts() {
        return totalPosts;
    }

    public long getTotalComments() {
        return totalComments;
    }

    public double getTopViralityScore() {
        return topViralityScore;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public void setTotalPosts(long totalPosts) {
        this.totalPosts = totalPosts;
    }

    public void setTotalComments(long totalComments) {
        this.totalComments = totalComments;
    }

    public void setTopViralityScore(
            double topViralityScore
    ) {
        this.topViralityScore =
                topViralityScore;
    }
}