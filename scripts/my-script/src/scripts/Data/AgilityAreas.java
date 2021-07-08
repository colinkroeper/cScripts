package scripts.Data;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public class AgilityAreas {


    /**************
     * TREE GNOME *
     **************/
    public static RSArea TG_START_FINISH_AREA = new RSArea(new RSTile(2488, 3435, 0), new RSTile(2471, 3440, 0));
    public static RSArea TG_START_AREA = new RSArea(new RSTile(2477, 3436, 0), new RSTile(2471, 3438, 0));
    public static RSArea TG_OBSTACLE_2 = new RSArea(new RSTile(2470, 3429, 0), new RSTile(2478, 3425, 0));
    public static RSArea TG_OBSTACLE_3 = new RSArea(new RSTile(2470, 3425, 1), new RSTile(2476, 3421, 1));
    public static RSArea TG_OBSTACLE_4 = new RSArea(new RSTile(2472, 3421, 2), new RSTile(2477, 3418, 2));

    public static RSArea TG_OBSTACLE_5 = new RSArea(new RSTile(2488, 3418, 2), new RSTile(2483, 3421, 2));
    public static RSArea TG_OBSTACLE_6 = new RSArea(new RSTile(2488, 3418, 0), new RSTile(2483, 3425, 0));
    public static RSArea TG_OBSTACLE_7 = new RSArea(new RSTile(2488, 3426, 0), new RSTile(2483, 3431, 0));
    public static RSArea WHOLE_TG_COURSE_LEVEL_0 = new RSArea(new RSTile(2469, 3440, 0), new RSTile(2490, 3413, 0));
    public static RSArea WHOLE_TG_COURSE_LEVEL_2 = new RSArea(new RSTile(2467, 3439, 2), new RSTile(2491, 3411, 2));
    public static RSArea WHOLE_TG_COURSE_LEVEL_1 = new RSArea(new RSTile(2468, 3431, 1), new RSTile(2488, 3416, 1));

    /**
     * DRAYNOR
     */

    public static RSArea DRAYNOR_GROUND_LEVEL = new RSArea(new RSTile(3105, 3239, 0), new RSTile(3080, 3282, 0));
    public static RSArea WHOLE_DRAYNOR_COURSE = new RSArea(new RSTile(3081, 3284, 3), new RSTile(3111, 3250, 3));
    public static RSArea ALL_OF_DRAYNOR_VILLAGE = new RSArea(new RSTile(3108, 3283, 0), new RSTile(3072, 3234, 0));
    public static RSArea DRAYNOR_START_AREA = new RSArea(new RSTile(3103, 3281, 0), new RSTile(3105, 3277, 0));
    public static RSArea DRAYNOR_LARGE_START_AREA = new RSArea(new RSTile(3102, 3283, 0), new RSTile(3107, 3273, 0));
    public static RSArea DRAYNOR_OBSTACLE_AREA_1 = new RSArea(new RSTile(3102, 3277, 3), new RSTile(3096, 3281, 3));
    public static RSArea DRAYNOR_OBSTACLE_AREA_2 = new RSArea(new RSTile(3093, 3273, 3), new RSTile(3088, 3278, 3));
    public static RSArea DRAYNOR_OBSTACLE_AREA_3 = new RSArea(new RSTile(3088, 3268, 3), new RSTile(3094, 3265, 3));
    public static RSArea DRAYNOR_OBSTACLE_AREA_4 = new RSArea(new RSTile(3088, 3256, 3), new RSTile(3084, 3261, 3));
    public static RSArea DRAYNOR_OBSTACLE_AREA_5 = new RSArea(new RSTile(3095, 3255, 3), new RSTile(3085, 3255, 3));
    public static RSArea DRAYNOR_OBSTACLE_AREA_6 = new RSArea(new RSTile(3086, 3256, 3), new RSTile(3095, 3254, 3));
    public static RSArea DRAYNOR_OBSTACLE_AREA_7 = new RSArea(new RSTile(3095, 3256, 3), new RSTile(3102, 3261, 3));

    /**********************
     /****** VARROCK *****
     /*******************/
    public static RSArea VARROCK_LARGE_START_AREA = new RSArea(new RSTile(3216, 3420, 0), new RSTile(3241, 3410, 0));
    //  public static    RSArea VARROCK_LARGE_START_AREA = new RSArea(new RSTile(3221, 3422, 0), new RSTile(3240, 3415, 0));
    public static RSArea VARROCK_GROUND_AREA = new RSArea(new RSTile(3190, 3436, 0), new RSTile(3240, 3388, 0));
    public static RSArea VARROCK_LEVEL_3 = new RSArea(new RSTile(3174, 3430, 3), new RSTile(3246, 3381, 3));
    public static RSArea VARROCK_LEVEL_1 = new RSArea(new RSTile(3174, 3444, 1), new RSTile(3272, 3359, 1));
    public static RSArea VARROCK_GROUND_START_AREA = new RSArea(new RSTile(3221, 3418, 0), new RSTile(3226, 3411, 0));
    public static RSArea VARROCK_END_GROUND_AREA = new RSArea(new RSTile(3229, 3419, 0), new RSTile(3243, 3409, 0));
    public static RSArea VARROCK_AREA_1 = new RSArea(new RSTile(3214, 3419, 3), new RSTile(3219, 3410, 3));
    public static RSArea VARROCK_AREA_2 = new RSArea(new RSTile(3200, 3419, 3), new RSTile(3208, 3413, 3));
    public static RSArea VARROCK_AREA_3 = new RSArea(new RSTile(3192, 3416, 1), new RSTile(3197, 3416, 1));
    public static RSArea VARROCK_AREA_4 = new RSArea(new RSTile(3192, 3406, 3), new RSTile(3198, 3402, 3));
    public static RSArea VARROCK_AREA_5 = new RSArea(new RSTile[]{
            new RSTile(3182, 3399, 3),
            new RSTile(3202, 3399, 3),
            new RSTile(3202, 3404, 3),
            new RSTile(3209, 3404, 3),
            new RSTile(3209, 3395, 3),
            new RSTile(3196, 3388, 3),
            new RSTile(3190, 3388, 3),
            new RSTile(3190, 3382, 3),
            new RSTile(3182, 3382, 3)
    }
    );
    // public static  RSArea[] VARROCK_AREA_5 = {new RSArea(new RSTile(3202, 3403, 3), new RSTile(3208, 3395, 3)), new RSArea(new RSTile(3202, 3398, 3), new RSTile(3182, 3390, 3))};
    public static RSArea VARROCK_AREA_6 = new RSArea(new RSTile(3214, 3402, 3), new RSTile(3232, 3393, 3));
    // public static  RSArea VARROCK_AREA_7 = new RSArea(new RSTile(3233, 3408, 3), new RSTile(3241, 3402, 3));
    public static RSArea VARROCK_AREA_7 = new RSArea(new RSTile(3232, 3402, 3), new RSTile(3218, 3393, 3));
    public static RSArea VARROCK_AREA_9 = new RSArea(new RSTile(3241, 3410, 3), new RSTile(3234, 3416, 3));
    public static RSArea VARROCK_FINAL_AREA = new RSArea(new RSTile(3241, 3409, 3), new RSTile(3235, 3416, 3));
    public static RSArea VARROCK_SECOND_LAST_AREA = new RSArea(new RSTile(3234, 3409, 3), new RSTile(3242, 3401, 3));
    public static   RSTile[] VARROCK_PATH_TO_START = {
            new RSTile(3237, 3417, 0),
            new RSTile(3222, 3415, 0)
    };

    /**********************
     /****** CANIFIS *****
     /*******************/
    public static RSArea CANIFIS_LARGE_START = new RSArea(new RSTile(3510, 3487, 0), new RSTile(3502, 3490, 0));
    public static RSArea CANIFIS_SMALL_START = new RSArea(new RSTile(3508, 3488, 0), new RSTile(3504, 3490, 0));
    public static RSArea ALL_CANIFIS = new RSArea(new RSTile(3469, 3509, 0), new RSTile(3526, 3462, 0));
    public static RSArea CANIFIS_OBSTACLE_1 = new RSArea(new RSTile(3511, 3489, 2), new RSTile(3503, 3499, 2));
    public static RSArea CANIFIS_OBSTACLE_2 = new RSArea(new RSTile(3504, 3507, 2), new RSTile(3495, 3503, 2));
    public static RSArea CANIFIS_OBSTACLE_3 = new RSArea(new RSTile(3493, 3505, 2), new RSTile(3483, 3498, 2));
    public static RSArea CANIFIS_OBSTACLE_4 = new RSArea(new RSTile(3473, 3500, 3), new RSTile(3480, 3491, 3));
    public static RSArea CANIFIS_OBSTACLE_5 = new RSArea(new RSTile(3476, 3487, 2), new RSTile(3484, 3481, 2));
    public static RSArea CANIFIS_OBSTACLE_6 = new RSArea(new RSTile(3486, 3479, 3), new RSTile(3504, 3468, 3));
    public static RSArea CANIFIS_OBSTACLE_7 = new RSArea(new RSTile(3508, 3474, 2), new RSTile(3516, 3483, 2));
    public static RSArea CANIFIS_FINISHED_AREA = new RSArea(new RSTile(3512, 3484, 0), new RSTile(3503, 3486, 0));
    public static RSArea ALL_CANIFIS_ROOFTOPS = new RSArea(new RSTile(3518, 3462, 3), new RSTile(3469, 3513, 3));
    public static RSArea ALL_CANIFIS_ROOFTOPS_LEVEL_2 = new RSArea(new RSTile(3518, 3465, 2), new RSTile(3471, 3508, 2));
    /***************
     **** SEERS ****
     **************/
    public static final RSArea SEERS_WALL_AREA = new RSArea(new RSTile(2730, 3487, 0), new RSTile(2727, 3489, 0));
    public static final RSTile[] SEERS_PATH_TO_WALL = {
            new RSTile(2718, 3468, 0),
            new RSTile(2726, 3476, 0),
            new RSTile(2728, 3483, 0),
            new RSTile(2729, 3488, 0)
    };
    public static final RSArea SEERS_EDGE_AREA = new RSArea(new RSTile(2698, 3465, 2), new RSTile(2702, 3460, 2));
    public static final RSArea SEERS_GAP_ONE_AREA = new RSArea(new RSTile(2721, 3497, 3), new RSTile(2730, 3490, 3));
    //public static final RSArea SEERS_GAP_TWO_AREA = new RSArea(new RSTile(2716, 3482, 2), new RSTile(2709, 3476, 2));
    public static final RSArea SEERS_GAP_TWO_AREA = new RSArea(new RSTile(2709, 3483, 2), new RSTile(2716, 3476, 2));
    public static final RSArea SEERS_GAP_THREE_AREA = new RSArea(new RSTile[]{
            new RSTile(2716, 3470, 3), new RSTile(2716, 3473, 3),
            new RSTile(2705, 3473, 3), new RSTile(2705, 3476, 3),
            new RSTile(2699, 3476, 3), new RSTile(2699, 3469, 3),
            new RSTile(2704, 3469, 3), new RSTile(2705, 3471, 3)
    });
    public static final RSArea SEERS_TIGHT_ROPE_AREA = new RSArea(new RSTile[]{
            new RSTile(2714, 3495, 2), new RSTile(2711, 3495, 2),
            new RSTile(2710, 3496, 2), new RSTile(2704, 3496, 2),
            new RSTile(2704, 3492, 2), new RSTile(2706, 3492, 2),
            new RSTile(2706, 3488, 2), new RSTile(2711, 3488, 2),
            new RSTile(2711, 3492, 2), new RSTile(2714, 3493, 2)});

    public static RSArea SEERS_END_AREA = new RSArea(new RSTile(2704, 3466, 0), new RSTile(2714, 3458, 0));
    public static RSArea UPSTAIRS_SEERS_BANK = new RSArea(new RSTile(2731, 3490, 1), new RSTile(2721, 3497, 1));


    /**
     * POLV area
     */
    public static RSArea POLV_LARGE_START_AREA = new RSArea(new RSTile(3349, 2964, 0), new RSTile(3356, 2959, 0));
    public static RSArea POLV_START_AREA = new RSArea(new RSTile(3349, 2962, 0), new RSTile(3352, 2959, 0));
    public static RSArea POLV_OBS_1_AREA = new RSArea(new RSTile(3345, 2968, 1), new RSTile(3351, 2962, 1));
    public static RSArea POLV_OBS_2_AREA = new RSArea(new RSTile(3351, 2972, 1), new RSTile(3356, 2977, 1));
    public static RSArea POLV_OBS_3_AREA = new RSArea(new RSTile(3362, 2976, 1), new RSTile(3359, 2979, 1));
    public static RSArea POLV_OBS_4_AREA = new RSArea(new RSTile(3365, 2976, 1), new RSTile(3371, 2973, 1));
    public static RSArea POLV_OBS_5_AREA = new RSArea(new RSTile(3369, 2981, 1), new RSTile(3364, 2986, 1));
    public static RSArea POLV_OBS_6_AREA = new RSArea(new RSTile(3365, 2979, 2), new RSTile(3354, 2985, 2));
    public static RSArea POLV_OBS_7_AREA = new RSArea(new RSTile[]{new RSTile(3371, 2990, 2), new RSTile(3363, 2990, 2), new RSTile(3363, 2991, 2), new RSTile(3357, 2991, 2), new RSTile(3357, 2996, 2), new RSTile(3371, 2996, 2)});
    public static RSArea POLV_OBS_8_AREA = new RSArea(new RSTile(3355, 3004, 2), new RSTile(3362, 3000, 2));
    public static RSArea ALL_POLV_LEVEL_0 = new RSArea(new RSTile(3340, 3005, 0), new RSTile(3377, 2944, 0));
    public static RSTile POLV_START_TILE = new RSTile(3351, 2962, 0);
    //RSTile POLV_START_TILE = new RSTile(3351, 2961,0);

}