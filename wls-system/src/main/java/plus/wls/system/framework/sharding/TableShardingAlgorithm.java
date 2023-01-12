// package plus.wls.system.framework.sharding;
//
// import lombok.extern.slf4j.Slf4j;
// import org.apache.shardingsphere.sharding.algorithm.sharding.classbased.ClassBasedShardingAlgorithm;
// import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
//
// import java.util.Collection;
// import java.util.Date;
//
///**
// * 表切分算法
// *
// * @author wls
// * @since 2021/11/16 16:07:00
// */
//@Slf4j
// public class TableShardingAlgorithm implements PreciseShardingAlgorithm {
//    @Override
//    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> shardingValue) {
//
//        System.out.println("table PreciseShardingAlgorithm ");
//        // 真实节点
//        availableTargetNames.stream().forEach((item) -> {
//            log.info("actual node table:{}", item);
//        });
//
//        log.info("logic table name:{},rout column:{}", shardingValue.getLogicTableName(), shardingValue.getColumnName());
//
//        //精确分片
//        log.info("column value:{}", shardingValue.getValue());
//
//
//        String tb_name = shardingValue.getLogicTableName() + "_";
//
//
//        // 根据当前日期 来 分库分表
//        Date date = shardingValue.getValue();
//        String year = String.format("%tY", date);
//        String mon =String.valueOf(Integer.parseInt(String.format("%tm", date))); // 去掉前缀0
//        String dat = String.format("%td", date);
//
//
//        // 选择表
//        tb_name = tb_name + year + "_" + mon;
//        System.out.println("tb_name:" + tb_name);
//
//        for (String each : availableTargetNames) {
//            System.out.println("t_order_:" + each);
//            if (each.equals(tb_name)) {
//                return each;
//            }
//        }
//
//        throw new IllegalArgumentException();
//    }
//}
