package com.peaceful.demo.es.sqlconvert;

import com.peaceful.common.util.chain.Command;
import com.peaceful.common.util.chain.Context;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * sql =
 * <p/>
 * Created by wangjun on 16/2/17.
 */
public class Eq implements Command {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean execute(Context context) throws Exception {
        String word = (String) context.get(SqlContextConst.CURRENT_WOLD);
        String[] words = (String[]) context.get(SqlContextConst.WORLDS);
        int pointer = (Integer) context.get(SqlContextConst.POINTER);
        BoolQueryBuilder queryBuilder = (BoolQueryBuilder) context.get(SqlContextConst.BOOLQUERY);
        if (word.indexOf("=") != -1 || word.toLowerCase().equals("!=")) {
            logger.debug("{} enter ne command...", word);
            String[] _word = word.split("=");
            String a, b;
            if (_word.length == 2) {
                a = _word[0];
                b = _word[1];
            } else if (_word.length == 0) {
                a = words[pointer - 1];
                b = words[pointer + 1];
            } else if (word.startsWith("=")) {
                a = words[pointer - 1];
                b = _word[0];
            } else {
                a = _word[0];
                b = words[pointer + 1];
            }
            logger.debug("compare : {} = {}", a, b);
            if (a.equals(b)) {
                // pass
            } else {
                queryBuilder.must(termQuery(a, b));
            }
            context.put(SqlContextConst.POINTER, ++pointer);
            if (pointer < words.length) {
                context.put(SqlContextConst.CURRENT_WOLD, words[pointer]);
            }
            return PROCESSING_COMPLETE;
        }
        return CONTINUE_PROCESSING;
    }
}
