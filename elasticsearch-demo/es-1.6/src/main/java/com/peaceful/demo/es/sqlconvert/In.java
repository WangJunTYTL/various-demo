package com.peaceful.demo.es.sqlconvert;

import com.peaceful.common.util.chain.Command;
import com.peaceful.common.util.chain.Context;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.elasticsearch.index.query.QueryBuilders.termsQuery;

/**
 * sql in 、 not in
 * <p/>
 * Created by wangjun on 16/2/17.
 */
public class In implements Command {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean execute(Context context) throws Exception {
        String word = (String) context.get(SqlContextConst.CURRENT_WOLD);
        String[] words = (String[]) context.get(SqlContextConst.WORLDS);
        int pointer = (Integer) context.get(SqlContextConst.POINTER);
        BoolQueryBuilder queryBuilder = (BoolQueryBuilder) context.get(SqlContextConst.BOOLQUERY);

        if (word.toLowerCase().equals("in")) {
            logger.debug("{} enter in command...", word);
            String a;
            boolean not = false;
            if (words[pointer - 1].trim().toLowerCase().equals("not")) {
                a = words[pointer - 2];
                not = true;
            } else {
                a = words[pointer - 1];
            }

            String _word = words[pointer + 1];
            if (_word.startsWith("(")) {
                if (_word.endsWith(")")) {
                    // pass
                } else {
                    while (true) {
                        pointer++;
                        if (words[pointer].endsWith(")")) {
                            _word += words[pointer];
                            break;
                        }
                    }
                }
            }
            _word = _word.substring(1, _word.length() - 1);
            _word = _word.replaceAll("'", "");
            _word = _word.replaceAll("\"", "");
            if (not) {
                queryBuilder.mustNot(termsQuery(a, _word.split(",")));

            } else {
                queryBuilder.must(termsQuery(a, _word.split(",")));
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
