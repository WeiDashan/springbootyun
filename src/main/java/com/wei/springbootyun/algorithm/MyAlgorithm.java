package com.wei.springbootyun.algorithm;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.slopeone.SlopeOneRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.util.List;

//基于Mahout实现协同过滤推荐算法
public class MyAlgorithm {
    //基于用户相似度
    public List<RecommendedItem> userrecommender(Integer userid,Integer size){
        File file = new File("src/main/java/com/wei/springbootyun/algorithm/ratings.txt");
        List<RecommendedItem> recommendedItems=null;
        try {
            DataModel dataModel = new FileDataModel(file);
            UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
            UserNeighborhood neighborhood = new ThresholdUserNeighborhood(1.0,similarity,dataModel);
            UserBasedRecommender recommender = new GenericUserBasedRecommender(dataModel,neighborhood,similarity);
            recommendedItems = recommender.recommend(userid,size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recommendedItems;
    }
    //基于内容相似度
    public List<RecommendedItem> itemrecommender(Integer userid,Integer size){
        File file = new File("src/main/java/com/wei/springbootyun/algorithm/ratings.txt");
        List<RecommendedItem> recommendedItems = null;
        try {
            DataModel dataModel = new FileDataModel(file);
            ItemSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
            Recommender recommender = new GenericItemBasedRecommender(dataModel,similarity);
            recommendedItems = recommender.recommend(userid,size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recommendedItems;
    }

    //基于Slope One的推荐引擎
    public List<RecommendedItem> myslopeoneRecommender(Integer userid,Integer size){
        File file = new File("src/main/java/com/wei/springbootyun/algorithm/ratings.txt");
        List<RecommendedItem> recommendations = null;
        try {
            DataModel dataModel = new FileDataModel(file);
            Recommender recommender = new CachingRecommender(new SlopeOneRecommender(dataModel));
            recommendations = recommender.recommend(userid,size);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return recommendations;
    }

}
